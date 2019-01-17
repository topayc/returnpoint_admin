<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="createBranchForm" style ="padding:5px;margin: 20px">
	<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >
	   <form id="createMemberBankAccountForm"  enctype="multipart/form-data" name = "createForm" method="post"  >
			<div style="margin-bottom:30px"><input id ="memberNo"  name="memberNo" style="width:100%" data-options="label:'회원 검색 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="memberName"  name="memberName" style="width:100%" data-options="label:'회원 이름 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="memberEmail"  name="memberEmail" style="width:100%" data-options="label:'회원 이메일 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="bankName"  name="bankName" style="width:100%" data-options="label:'은행 이름 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="accountOwner"  name="accountOwner" style="width:100%" data-options="label:'계좌 소유주 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="bankAccount"  name="bankAccount" style="width:100%" data-options="label:'은행 계좌',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px">
				<select id ="accountStatus" name="accountStatus"   style="width:100%" data-options="label:'계좌 상태',labelWidth :140,labelPosition : 'left'"> 
					<c:forEach var="memberBankAccountStatus"  items="${memberBankAccountStatusList}" varStatus="status">
						   <c:if test = "${ memberBankAccountStatus.useInAdmin == 'Y' }">
							  <option value='${memberBankAccountStatus.key}' >${memberBankAccountStatus.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
			<div style="margin-bottom:30px"><input id ="statusMessage"  name="statusMessage" style="width:100%" data-options="label:'상태 메시지',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:20px"> 
				<select class = "easyui-combobox"  id ="regType" name="regType"   style="width:100%" data-options="label:'등록 타입',labelWidth :140,labelAlign:'left',labelPosition : 'left'" style="width:100%;height:30px"> 
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
	            	});
				}
			}]
		});
		
		$('#memberName').textbox({
			editable : false,
		});

		$('#memberEmail').textbox({
			editable : false,
		});
		$('#bankName').textbox({});
		$('#accountOwner').textbox({});
		$('#bankAccount').textbox();
		$('#statusMessage').textbox();
		$('#accountStatus').combobox({
			showItemIcon: true,
            editable: false,
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