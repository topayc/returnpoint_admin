<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<div style = "padding : 3px" id = "formContainer">
	   <c:if test="${not empty parBoard}">
	   <div id = "ori_board"   style="width : 770px;margin-bottom:10px;" title = "[원글정보] : ${parBoard.boardTitle}">
	  		<div>
		  		<div style = "padding-left:10px;padding-right:10px;padding-top:5px; padding-bottom:5px;font-weight : bold;">
		  		<p> * 분류 : ${parBoard.boardCate}</p>
		  		<p> * 상호명 : ${parBoard.rerv1}</p>
		  		<p> * 대표자 : ${parBoard.rerv2}</p>
		  		<p> * 주소 : ${parBoard.rerv3}</p>
		  		<p> * 담당자 : ${parBoard.rerv4}</p>
		  		<p> * 연락처(전화)  : ${parBoard.rerv5}</p>
		  		<p> * 이메일 : ${parBoard.rerv6}</p>
		  		<!-- <hr color=#dddddd noshade/> -->
		  		</div>
		  		<div style = "border-top : 1px solid #dddddd;padding-left:10px;padding-right:10px;padding-top:5px; padding-bottom:5px;">
		  			<p style = "font-weight : bold">${parBoard.boardContent} </p>
		  		</div>
	  		</div>
	   </div>
	   </c:if>
	   <form id="createBoardForm"  enctype="multipart/form-data" method="post" >
			<div id  ="boardContent"  class="easyui-texteditor" style="height:400px;width : 770px;padding:10px">
				${replyBoard.boardContent}
			</div>
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
	
	$('#boardType').combobox({
		 multiple:false,
		 editable : false,
         panelHeight:'auto',
         onSelect : function(record){
        	 if (record.value == "2" ||  record.value == "3") {
        		 $('#boardCate').combobox({disabled : false});
        	 }else {
        		 $('#boardCate').combobox({disabled : true});
        	 }
         }
	});
	
	$('#boardCate').combobox({
		multiple:false,
		editable : false,
        panelHeight:'auto'
	});
	$('#ori_board').panel();
	$('.easyui-texteditor').texteditor();
}
</script>