<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="createNode" style ="padding:5px;margin: 20px">
	<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >
	   <form id="createMembershipRequest"  enctype="multipart/form-data" name = "createForm" method="post"  >
			
			<div style="margin-bottom:30px">
				<input class = "easyui-textbox"  id ="memberNo"  name="memberNo" style="width:100%" data-options="label:'회원 검색 ',labelWidth :140,labelPosition : 'left'"> 
			</div>
			
			<div style="margin-bottom:30px">
				<input class = "easyui-textbox"  id ="memberEmail"  name="memberEmail" style="width:100%" data-options="label:'회원 이메일 ',labelWidth :140,labelPosition : 'left'"> 
			</div>
			
			<div style="margin-bottom:30px">
				<input class = "easyui-textbox"  id ="depositor"  name="depositor" style="width:100%" data-options="label:'입금자 명 ',labelWidth :140,labelPosition : 'left'"> 
			</div>
			
			<div style="margin-bottom:30px">
				<input class = "easyui-combobox"  id ="paymentAmount"  value = "${policy.membershipTransLimit}" name="paymentAmount" style="width:100%" data-options="label:'결제 금액 ',labelWidth :140,labelPosition : 'left'"> 
			</div>
			
			<div style="margin-bottom:30px">
				<select id ="paymentType" name="paymentType"   style="width:100%" data-options="label:'결제 방법',labelWidth :140,labelPosition : 'left'"> 
					<c:forEach var="paymentType"  items="${paymentTypes}" varStatus="status">
						  <c:if test = "${ paymentType.useInAdmin == 'Y' }">
							  <option value='${paymentType.key}' >${paymentType.value} </option>
						  </c:if>
					</c:forEach>
				</select>
			</div>
			
			<div style="margin-bottom:30px">
				<select id ="companyBankAccountNo" name="companyBankAccountNo"   style="width:100%" data-options="label:'입급 은행',labelWidth :140,labelPosition : 'left'"> 
					<c:forEach var="bank"  items="${bankAccounts}" varStatus="status">
						   <option value='${bank.companyBankAccountNo}' >${bank.bankName} - ${bank.bankAccount} - ${bank.bankOwnerName} </option>
					</c:forEach>
				</select>
			</div>	
			
			
			<div style="margin-bottom:30px">
				<select id ="paymentStatus" name="paymentStatus"   style="width:100%" data-options="label:'결제 상태',labelWidth :140,labelPosition : 'left'"> 
					<c:forEach var="paymentStatus"  items="${paymentStatuses}" varStatus="status">
						  <c:if test = "${ paymentStatus.useInAdmin == 'Y' }">
							  <option value='${paymentStatus.key}' >${paymentStatus.value} </option>
						  </c:if>
					</c:forEach>
				</select>
			</div>
			
			<div style="margin-bottom:30px">
				<select class = "easyui-combobox"  id ="regType" name="regType"   style="width:100%" data-options="label:'등록 타입',labelWidth :140,labelPosition : 'left'"> 
					<c:forEach var="registType"  items="${registTypes}" varStatus="status">
						  <c:if test = "${ registType.useInAdmin == 'Y' }">
							  <option value='${registType.key}' >${registType.value} </option>
						  </c:if>
					</c:forEach>
				</select>
			</div>
		
		</form>
	</div>
</div>
<script>

	
	function setViewInit(){
		$('#memeberStatusContainer').panel();
		
		$('#memberNo').textbox({
			prompt: '회원 검색',
			editable : false,
			icons:[{
				iconCls:'icon-search',
				handler: function(e){
					var v = $(e.data.target).textbox('getValue');
					loadNodeListView({
	            		targetElem : "#dlgForm2",
	            		title : "회원",
	            		queryOptions : {
	            			memberEmail : v,
	            			viewReqName : "nodeList",
	            			searchNodeType :  "1"
	            		}
	            	}, function callback(selNode){
	            		$(e.data.target).textbox('setValue', selNode.memberNo);
	            		$('#memberEmail').textbox('setValue', selNode.memberEmail);
	            	});
				}
			}]
		});
		
		$('#memberEmail').textbox({
			editable : false
		});
		$('#paymentAmount').textbox({
			editable : false,
		});
		$('#depositor').textbox({
		});
		

		$('#paymentStatus').combobox({
			showItemIcon: true,
            editable: false,
            panelHeight: 'auto',
            label: '결제 상태',
            multiple:false,
            required:true
		});

		$('#paymentType').combobox({
			showItemIcon: true,
            editable: false,
            panelHeight: 'auto',
            label: '결제 방법',
            multiple:false,
            required:true
		});

		$('#companyBankAccountNo').combobox({
			showItemIcon: true,
            editable: false,
            panelHeight: 'auto',
            label: '입금 은행 선택',
            multiple:false,
            required:true
		});
		
		$('#regType').combobox({
			showItemIcon: true,
            readonly : true,
            panelHeight: 'auto',
            label: '등록 유형',
            multiple:false,
            required:true
		});
		$('#regType').combobox('select',"A");
		
		$('#createMembershipRequest').form({
		    url: '/api/membershipRequest/create',
		    onSubmit: function(){
		    },
		    success:function(data){
		        alert(data)
		    }
		});
	}
	setViewInit();
</script>