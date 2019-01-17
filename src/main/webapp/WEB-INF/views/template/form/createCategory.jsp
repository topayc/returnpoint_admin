<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div  style ="padding:5px;margin: 20px">
	<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >
	   <form id="createCategoryForm"  enctype="multipart/form-data" name = "createCategoryForm" method="post" >
			<input type = "hidden" id ="categoryLevel"  name="categoryLevel"  value = "${categoryLevel}"/> 
			<div style="margin-bottom:30px"><input value = "${parentCategoryNo}" class="easyui-textbox"  id ="parentCategoryNo"  name="parentCategoryNo" style="width:100%" data-options="label:'상위 카테고리 번호 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input value = "${parentCategoryName}" class="easyui-textbox"  id ="parentCategoryName"  name="parentCategoryName" style="width:100%" data-options="label:'상위 카테고리 이름 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input class="easyui-textbox"  id ="categoryName"  name="categoryName" style="width:100%" data-options="label:'카테고리 이름 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"><input class="easyui-textbox"  id ="categoryNameEn"  name="categoryNameEn" style="width:100%" data-options="label:'카테고리 영문 이름 ',labelWidth :140,labelPosition : 'left'"> </div>
			<div style="margin-bottom:30px"> 
				<select class = "easyui-combobox"  id ="categoryStatus" name="categoryStatus"   style="width:100%" data-options="label:'카테고리 상태',labelWidth :140,labelAlign:'left',labelPosition : 'left'"> 
					<c:forEach var="categoryStatus"  items="${categoryStatusList}" varStatus="status">
						   <c:if test = "${ categoryStatus.useInAdmin == 'Y' }">
							  <option value='${categoryStatus.key}' >${categoryStatus.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>
		</form>
	</div>
</div>
<script>
	function setViewInit(){
		
		$('.easyui-textbox').textbox();
		$('#categoryStatus').combobox({
			showItemIcon: true,
            editable: false,
            panelHeight: 'auto',
            multiple:false,
            required:true
		});
		
		if ($("#categoryLevel").val() == "1") {
			$("#parentCategoryNo").textbox({
				prompt : "해당 사항 없음",
				disabled : true,
				value :''
			});
			
			$("#parentCategoryName").textbox({
				disabled : true,
				prompt : "해당 사항 없음",
				value :''
			});
		}
	}
	setViewInit();
</script>