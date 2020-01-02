<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="easyui-layout" data-options="fit:true,border:false,split:true">
	<div  data-options="region:'center',split:true"  > 
		<table id = "node_list" style ="width:100%;height:100%">
		</table> 
	</div>
</div>
<div id="board_create_container"  style = "padding : 15px;display : none">
	<form id="createBoardForm"  enctype="multipart/form-data" name = "createBoardForm" method="post" >
	<input  type = "hidden" id = "mainBbsNo"  name="mainBbsNo"  value = "0"/>
	<input  type = "hidden" id = "bbsType1"  name="bbsType1"  value = "1"/>
  	
  	
  	<div style="margin-top:10px;margin-bottom:20px;margin-left : 10px;">
		<input  id = "title"  name="title" style="width:100%"/>
	</div>
	
	<div style="margin-bottom: 20px; margin-left: 10px;">
			<select id=bbsType2 class="easyui-combobox" name="bbsType2"  style="width: 100%">
				<option value="1">전체 공지</option>
				<option value="2">가맹점 공지</option>
			</select>
		</div>

		<div style="margin-bottom:20px;margin-left : 10px;">
		<textarea  id = "content"  name="content" style="width:100%;height: 400px"></textarea> 
	</div>
	</form>
</div>

<div id="board_reply_container"  style = "padding : 5px;display : none">
	<div   style="height:40%;border : 1px soild #888888;padding : 10px;background-color : #eee; color : #000" data-options="border:true"> 
		<div id="board_content_ori"  > </div>
	</div>
	
	<div   class = "easyui-panel"  style = "margin-top: 10px">
		<form id="board_reply_form"  enctype="multipart/form-data" name = "board_reply_form" method="post" >
	  	<div >
			<textarea  id = "board_reply"  style="width:100%;height: 260px" placeholder = "답글 입력"></textarea> 
		</div>
		</form>
	</div>
</div>

<div id="board_view_container"  style = "padding : 15px;display : none">
	<div id="board_view"  class = "easyui-panel" >
		<div id="board_content" > </div>
	</div>
</div>

<script>
var searchFormData = {
		nodeType :  '${searchCondition.searchNodeType}'
	};
	var nodeTypeArrs = {
		"1" : "일반 회원",
			"2" : "정회원 (추천인)",
			"3" : "지사",
			"4" : "대리점", 
			"5" : "협력업체(가맹점)", 
			"6" : "영업 관리자" 	
	}
	/* 	1 : 일반 회원
		2 : 정회원 (추천인)
		3 : 지사
		4 : 대리점 
		4 : 협력업체(가맹점_ 
		5 : 영업 관리자 */
		</script>]
<script src="resources/js/${viewReqName}.js"></script>
