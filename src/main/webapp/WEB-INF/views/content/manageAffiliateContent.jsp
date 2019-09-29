<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="easyui-layout" data-options="fit:true,border:false,split:true">
	<div  data-options="region:'north',split:true"  id = "member_search"  style ="height:100px" title ="정보 등록 가맹점 검색" >
		 <div class="easyui-panel" title="" style="width:100%;padding:10px 15px;">
	        <form id="searchForm" method="post">
	             <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <select id = "searchAffiliate" class="easyui-combobox" name="searchAffiliate"  style="width:100%">
	                	<c:forEach var="affiliate" items="${affiliateList}" varStatus="status">	
						   <option value="${affiliate.affiliateNo}"  >${affiliate.affiliateName}</option>
						</c:forEach>
	                </select>	
	            </div>
	            
	            <div style="padding:5px 0;display:inline-block;">
		            <a href="javascript:void(0)" id = "search_btn"  style="width:60px;margin-left: 10px;">검색</a>
		            <a href="javascript:void(0)" id = "reset_btn"   style="width:60px;margin-left: 2px;">리셋</a>
	       	 	</div>
	       	 	</form>
    	</div>
	</div>
	
	<div  data-options="region:'center',split:true"  title = "협력업체 세부 정보" > 
		<div class="easyui-panel"   style ="width:100%;height:100%">
		</div> 
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
