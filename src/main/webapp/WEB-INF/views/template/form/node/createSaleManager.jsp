<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div  style ="padding:5px;margin: 20px">
	<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >
	   <form id="createSaleManagerForm"  enctype="multipart/form-data" name = "createForm" method="post" action = "<c:url value='/createAbmsProject'/>">
			<div style="margin-bottom:30px"><input id ="memberNo"  name="memberNo" style="width:100%" data-options="label:'회원 검색 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="saleManagerEmail"  name="saleManagerEmail" style="width:100%" data-options="label:'영업 관리자 이메일 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="saleManagerName"  name="saleManagerName" style="width:100%" data-options="label:'영업 관리자 이름',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="saleManagerAddress"  name="saleManagerAddress" style="width:100%" data-options="label:'영업 관리자 주소',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="saleManagerTel"  name="saleManagerTel" style="width:100%" data-options="label:'영업 관리자 전화번호',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input id ="saleManagerPhone"  name="saleManagerPhone" style="width:100%" data-options="label:'영업 관리자 모바일',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"> 
				<select class = "easyui-combobox"  id ="saleManagerStatus" name="saleManagerStatus"   style="width:100%" data-options="label:' 영업 관리자 상태',labelWidth :140,labelAlign:'left',labelPosition : 'left'"> 
					<c:forEach var="nodeStatus"  items="${nodeStatuses}" varStatus="status">
						   <c:if test = "${ nodeStatus.useInAdmin == 'Y' }">
							  <option value='${nodeStatus.key}' >${nodeStatus.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
	
			<div style="margin-bottom:30px"> 
				<select class = "easyui-combobox"  id ="regType" name="regType"   style="width:100%" data-options="label:'등록 타입',labelWidth :140,labelAlign:'left',abelPosition : 'left'" style="width:100%;height:30px"> 
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
	            		$("#saleManagerEmail").textbox('setValue', selNode.memberEmail);
	            		$("#saleManagerName").textbox('setValue', selNode.memberName);
	            	});
				}
			}]
		});

		
		$('#saleManagerEmail').textbox({
			prompt: '영업 관리자 이메일', 
			editable : false,
		});
		
		$('#saleManagerName').textbox({
			prompt: '영업 관리자 상호 및 기타 이름', 
		});
		$('#saleManagerAddress').textbox({
			prompt: '영업 관리자 주소', 
		});
		$('#saleManagerTel').textbox({
			prompt: '영업 관리자 전화 번호', 
		});
		$('#saleManagerPhone').textbox({
			prompt: '영업 관리자 핸드폰 번호', 
		});
		$('#saleManagerStatus').combobox({
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