<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div  style ="padding:5px;margin: 5px">
	<div style = "padding:3px;padding-top:1px;padding-bottom:5px" id = "faqFormContainer">
	   <form id="createBoardForm"  enctype="multipart/form-data" method="post" >
			<div style="margin-bottom:20px"> 
				<select class = "easyui-combobox"  id ="boardType" name="boardType"   style="width:100%" data-options="label:'게시판 종류',labelWidth :140,labelAlign:'left',labelPosition : 'left'" style="width:100%;height:30px"> 
					<c:forEach var="boardType"  items="${boardTypeList}" varStatus="status">
						    <c:if test = "${ boardType.useInAdmin == 'Y' }">
							   <option value='${boardType.key}' >${boardType.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>	
			<div style="margin-bottom:20px" id ="board_cate"> 
				<select class = "easyui-combobox"  id ="boardCate"  name="boardCate"   style="width:100%" data-options="label:'게시판 카테고리',labelWidth :140,labelAlign:'left',labelPosition : 'left'" style="width:100%;height:30px"> 
					<c:forEach var="boardCateItem"  items="${boardCates}" varStatus="status">
						    <c:if test = "${ boardCateItem.useInAdmin == 'Y' }">
							    <option value='${boardCateItem.key}' >${boardCateItem.value} </option>
						  </c:if>
					</c:forEach>
				</select> 
			</div>	
			<div style="margin-bottom:20px" > 
				<select class = "easyui-combobox"  id ="isPublic" name="isPublic"   style="width:100%" data-options="label:'공개 여부',labelWidth :140,labelAlign:'left',labelPosition : 'left'" style="width:100%;height:30px"> 
					  <option value="Y">공개</option>
					  <option value="N">비공개</option>
				</select> 
			</div>	
			<div style="margin-bottom:20px">
				<input class = "easyui-textbox"  id ="boardTitle"  name="boardTitle" style="width:100%" data-options="label:'제목 ',labelWidth :140,labelPosition : 'left'"> 
			</div>
			<div id  ="boardContent"  class="easyui-texteditor" style="height:400px;width : 750px;padding:20px"></div>
		</form>
	</div>
</div>
<script>
$(document).ready(function(){
	setViewInit();
});
function setViewInit(){
	$('.easyui-panel').panel();
	$('.easyui-textbox').textbox();
	
	$('#boardCate').combobox({	
		multiple:false,
		editable : false,
        panelHeight:'auto',
        valueField : 'key',
		textField: 'value'
	});
	$('#isPublic').combobox({
		multiple:false,
		editable : false,
        panelHeight:'auto'
	});
	
	$('#boardContent').texteditor();
}

$(document).ready(function(){
	$('#boardType').combobox({
		multiple:false,
		editable : false,
        panelHeight:'auto',
        onSelect : function(record){
       	 if (record.value == "1") {
       		 $('#boardCate').combobox("clear");
       	 }else {
       			returnp.api.call("getBoardCategories", {boardType : record.value}, function(res){
       				if (res.resultCode == "100") {
       					 $('#boardCate').combobox("clear");
       					 var data = [];
       					 for (var i = 0; i < res.rows.length ; i++){
                            	data.push({key : res.rows[i].key, value : res.rows[i].value});
                            }
       					 $('#boardCate').combobox('loadData', data);
       					 $('#boardCate').combobox('select', '2');
       				}else {
       					$.messager.alert('오류 발생', res.message);
       				}
       			});
       		
       	 }
        }
	});
})
</script>