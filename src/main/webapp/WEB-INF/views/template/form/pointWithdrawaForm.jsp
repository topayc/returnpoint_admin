<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="createBranchForm" style ="padding:5px;margin: 25px">
		<input type = "hidden"  id ="rPayWithdrawalMinLimit"  name="rPayWithdrawalMinLimit"  value = "${policy.rPayWithdrawalMinLimit}" />
	   <input type = "hidden"  id ="rPayWithdrawalMaxLimit"  name="rPayWithdrawalMaxLimit" value = "${policy.rPayWithdrawalMaxLimit}" />
	<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >
	   <form id="createPointWithdrawalForm"  enctype="multipart/form-data" name = "createPointWithdrawalForm" method="post"  >
			<div style="margin-bottom:25px"><input id ="memberNo"  name="memberNo" style="width:100%" data-options="label:'회원 검색 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:25px"><input id ="memberName"  name="memberName" style="width:100%" data-options="label:'회원 이름 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:25px"><input id ="memberEmail"  name="memberEmail" style="width:100%" data-options="label:'회원 이메일 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:25px">
				<select id ="withdrawalPointType" name="withdrawalPointType"   style="width:100%" data-options="label:'출금 포인트 타입',labelWidth :140,labelPosition : 'left'"> 
					  <option value= 'R_PAY' >R_PAY</option>
				</select> 
			</div>
			<div style="margin-bottom:20px">
				<span id = "withdrawalMinMax">
					<strong>* R PAY는 최저  
					<fmt:formatNumber value="${policy.rPayWithdrawalMinLimit}" pattern="###,###,###,###"/> 이상
					<fmt:formatNumber value="${policy.rPayWithdrawalMaxLimit}" pattern="###,###,###,###"/> 까지 출금 가능합니다.
					</strong>
				</span> 
			</div>
			<div style="margin-bottom:25px"><input id ="rPayBalance"  name="rPayBalance"  value = "${redPointCommand.pointAmount}" style="width:100%" data-options="label:'보유 R_PAY',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:25px"><input id ="withdrawalAmount"  name="withdrawalAmount" style="width:100%" data-options="label:'출금 금액',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:25px">
				<select id ="memberBankAccountNo" name="memberBankAccountNo"   style="width:100%" data-options="label:'출금 계좌',labelWidth :140,labelPosition : 'left'"> 
				</select> 
			</div>
			<div style="margin-bottom:25px">
				<select id ="withdrawalStatus" name="withdrawalStatus"   style="width:100%" data-options="label:'출금 상태',labelWidth :140,labelPosition : 'left'"> 
					<c:forEach var="withdrawalStatus"  items="${withdrawalStatusList}" varStatus="status">
						   <c:if test = "${ withdrawalStatus.useInAdmin == 'Y' }">
							  <option value='${withdrawalStatus.key}' >${withdrawalStatus.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
			<div style="margin-bottom:25px"><input id ="withdrawalMessage"  name="withdrawalMessage" value = "빠른 처리 요망합니다"  style="width:100%" data-options="label:'출금 메시지',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:25px"> 
				<select class = "easyui-combobox"  id ="regType" name="regType"   style="width:100%" data-options="label:'등록 타입',labelWidth :140,labelAlign:'left',labelPosition : 'left'" style="width:100%;height:25px"> 
					<c:forEach var="reigistType"  items="${registTypes}" varStatus="status">
						    <c:if test = "${ reigistType.useInAdmin == 'Y' }">
							  <option value='${reigistType.key}' >${reigistType.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
			
		</form>
	</div>
</div>
<script>
	function setViewInit(){
		$('#memberNo').textbox({
			prompt: '참조할 회원 검색 ',
			editable : false,
			icons:[{
				iconCls:'icon-search',
				handler: function(e){
					var v = $(e.data.target).textbox('getValue');
					loadNodeListView({
	            		targetElem : "#dlgForm2",
	            		title : "회원 검색",
	            		queryOptions : {
	            			memberEmail : v,
	            			viewReqName : "nodeList",
	            			searchNodeType :  "1"
	            			
	            		}
	            	},function callback(selNode){
	            		$(e.data.target).textbox('setValue', selNode.memberNo);
	            		$('#memberEmail').textbox('setValue', selNode.memberEmail);
	            		$('#memberName').textbox('setValue', selNode.memberName);
	            		
	            		/*선택한 회원의 R PAY 조회*/
	            		returnp.api.call("getRedPointCommand", {memberNo : selNode.memberNo}, function(res){
	                		if (res.resultCode  == "100") {
	                			$('#rPayBalance').textbox('setValue', res.data.pointAmount);
	                		}else {	
	                		}
	                	});

	            		/*선택한 회원의 출금 계좌 조회*/
	            		returnp.api.call("getMemberBankAccounts", {memberNo : selNode.memberNo}, function(res){
	                		console.log("출금 계좌 리스트");
	                		console.log(res)
	            			if (res.resultCode  == "100") {
	            				$('#memberBankAccountNo').combobox("clear");
                				var data = [];
                				
                				for (var i = 0; i < res.rows.length ; i ++){
                					data.push({
                						memberBankAccountNo: res.rows[i].memberBankAccountNo , 
                						memberBankAccountText : "["+ res.rows[i].accountOwner + "] - " + res.rows[i].bankName + " -  " + res.rows[i].bankAccount   
                					});
                					
                				}
								data.push()
								$('#memberBankAccountNo').combobox('loadData', data);
								$('#memberBankAccountNo').combobox('select', '1');
	            			}else {	
	                		}
	                	});
	            	});
				}
			}]
		});

		$('#memberBankAccountNo').combobox({
			showItemIcon: true,
            editable: false,
            panelHeight: 'auto',
            multiple:false,
            required:true,
            valueField: 'memberBankAccountNo',
            textField: 'memberBankAccountText',
		});
		
		$('#memberName').textbox({
			editable : false,
		});

		$('#memberPhone').textbox({
			editable : false,
		});

		$('#memberEmail').textbox({
			editable : false,
		});
		$('#withdrawalAmount').textbox();
		$('#rPayBalance').textbox({
			readonly : true,
			editable : false
		});
		$('#withdrawalMessage').textbox();
		$('#withdrawalPointType').combobox({
			showItemIcon: true,
            panelHeight: 'auto',
            multiple:false,
            readonly : true,
            required:true
		});
		$('#memberBankAccountNo').combobox({
			showItemIcon: true,
            panelHeight: 'auto',
            multiple:false,
            required:true
		});
		$('#withdrawalStatus').combobox({
			showItemIcon: true,
            panelHeight: 'auto',
            multiple:false,
            required:true
		});
		$('#regType').combobox({
			showItemIcon: true,
            readonly : true,
            panelHeight: 'auto',
            multiple:false,
            required:true
		});
		$('#regType').combobox('select',"A");
	}
	setViewInit();
</script>