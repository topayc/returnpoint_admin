<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="easyui-layout" data-options="fit:true,border:false,split:true">
	<div id="manual_regist_container" style ="padding:5px">
		<div style = "padding:20px 30px;width : 40%" >
		   <form id="uploadFileForm"   name = "uploadFileForm"  method="post" enctype="multipart/form-data"  action = "<c:url value='/api/fileUpload/create'/>">
				<div style="margin-bottom:30px">
	                  <select id = uploadNodeFileType  name="uploadNodeFileType"  class="easyui-combobox"   style="width:100%" >
			              <option value = "100">노드별 코드 자동 등록</option>
	    		          <option value = "200">노드별 정보 자동 등록</option>
	                </select>
	              </div>  
				<div style="margin-bottom:30px">
	                <select id = "uploadNodeType"  name="uploadNodeType"  class="easyui-combobox"   style="width:100%" >
	                	<option value = "100">통합 노드 </option>
	                	<c:forEach var="nodeType" items="${nodeTypeList}" varStatus="status">
						   <option value="${nodeType.key}" <c:if test="${nodeType.key == searchCondition.searchNodeType}">selected="selected"</c:if>><strong>${nodeType.value} 파일</strong></option>
						</c:forEach>
	                </select>
				</div>
				<div style="margin-bottom:30px"><input id = "uploadFile" name = "uploadFile" class="easyui-filebox" style="width:502px"></div>
				<div style="margin-bottom:30px;width : 500px"><span>* 엑셀 포맷 파일만 업로드 가능</span></div>
				<div style="margin-bottom:30px"><a id="uploadBtn" href="#">파일 업로드&nbsp;</a></div>
			</form>
		</div>
	</div>
</div>
<script>
/* 검색어 타입 셀렉트 박스  초기화*/
$(document).ready(function(){
	$('#uploadNodeFileType').combobox({
		labelPosition : 'left',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		multiple:false,
		required:true,
		labelWidth : 150,
		width : 500,
	});
	
	$('#uploadNodeType').combobox({
		labelPosition : 'left',
		showItemIcon: true,
		editable: false,
		panelHeight: 'auto',
		multiple:false,
		required:true,
		labelWidth : 150,
		width : 500,
	});

	$('#uploadBtn').linkbutton({
	    iconCls: 'icon-ok'
	});
	
	$('#uploadFile').linkbutton({
	    iconCls: 'icon-search'
	});	
	
	$('#uploadFile').filebox({
	    buttonText: '파일 선택&nbsp;',
	    buttonAlign: 'left',
	    buttonIcon : "fas fa-file-medical",
	    onChange : function(newValue, oldValue){
		}
	});
	
	$('#uploadFileForm').form({
		onSubmit : function(param){},
		success : function(data){
			 var res = JSON.parse(data);
			 $.messager.alert('알림', res.message);
		}
	});
	
});

function uploadFile(){
	$('#"uploadFileForm"').form('submit');
	//$('#"uploadFileForm"').submit();
}

</script>