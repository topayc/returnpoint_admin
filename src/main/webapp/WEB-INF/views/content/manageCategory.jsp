<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="easyui-layout" data-options="fit:true,border:false,split:true">
	<div  data-options="region:'north',split:true" title ="* 카테고리 1" style  = "height:48%" > 
		<table id = "cate1_list" style ="width:100%;height:100%">
		</table> 
	</div>
	<div  data-options="region:'center',split:true"  id = "member_search"  style ="height:52%"  title ="* 카테고리 2" >
		 <table id = "cate2_list" style ="width:100%;height:100%">
		</table> 
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
		</script>
<script src="resources/js/${viewReqName}.js"></script>
