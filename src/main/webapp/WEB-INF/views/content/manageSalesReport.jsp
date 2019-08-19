<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="easyui-layout" data-options="fit:true,border:false,split:true">
	<div  data-options="region:'north',split:true"  id = "member_search"  style ="height:150px" title ="결제 검색" >
		 <div class="easyui-panel" title="" style="width:100%;padding:10px 15px;">
	        <form id="searchForm" method="post">
	       <!--         <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <select id = "searchDateType" class="easyui-combobox" name="searchDateType" label="검색 기간 타입 " style="width:100%">
	                	 <option value = "day">일별 검색</option>
	                	 <option value = "month">월별 검색</option>
	                	 <option value = "year">연별 검색</option>
	                </select>	
	            </div> -->
	            
	            <div style="margin-bottom:10px;margin-left : 10px;display:inline-block">
	                <input  id = "searchDateStart" name="searchDateStart" label="검색 시작일" style="width:100%"/>
	            </div>
	            
	            <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <input id = "searchDateEnd"  name="searchDateEnd" label="검색 종료일" style="width:100%"/>
	            </div>
	             
	             <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <select id = "searchAffiliate" class="easyui-combobox" name="searchAffiliate" label="조회 업체 " style="width:100%">
	                	 <option value = "0">전체</option>
	                	<c:forEach var="affiliate" items="${affiliateList}" varStatus="status">	
						   <option value="${affiliate.affiliateNo}"  >${affiliate.affiliateName}</option>
						</c:forEach>
	                </select>	
	            </div>
	            
	             <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <select id = "searchPaymentTransactionType" class="easyui-combobox" name="searchPaymentTransactionType" label="결제 타입" style="width:100%">
	                	 <option value = "0">전체</option>
	                	<c:forEach var="paymentTransactionType" items="${paymentTransactionTypeList}" varStatus="status">	
						   <option value="${paymentTransactionType.key}"  >${paymentTransactionType.value}</option>
						</c:forEach>
	                </select>	
	            </div>
	            
	             <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <select id = "searchPaymentApprovalStatus" class="easyui-combobox" name="searchPaymentApprovalStatus" label="결제 승인 상태" style="width:100%">
	                	 <option value = "0">전체</option>
	                	<c:forEach var="paymentApprovalStatus" items="${paymentApprovalStatusList}" varStatus="status">
						   <option value="${paymentApprovalStatus.key}"  >${paymentApprovalStatus.value}</option>
						</c:forEach>
	                </select>	
	            </div>

			<%-- 	<div style="margin-bottom:10px;margin-left : 10px;display:inline-block;width:200px">
	                <select id = "paymentType" class="easyui-combobox" name="paymentType" label="결제 유형" style="width:100%">
	                	 <option value="0">전체</option>
	                	<c:forEach var="paymentType" items="${paymentTypeList}" varStatus="status">
						   <option value="${paymentType.key}"  >${paymentType.value}</option>
						</c:forEach>
	                </select>
	            </div> --%>
	            	            
	            <%--  <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;width:200px">
	                <select id = "keywordType" class="easyui-combobox" name="keywordType" label="검색 유형" style="width:100%">
	                	 <option value="0">전체</option>
	                	<c:forEach var="keywordType" items="${keywordTypeList}" varStatus="status">
						   <option value="${keywordType.key}" <c:if test="${keywordType.key == nodeSearch.keywordType}">selected="selected"</c:if> >${keywordType.value}</option>
						</c:forEach>
	                </select>
	            </div> --%>
	            
	            <div style="margin-bottom:10px;margin-left:7px;;display:inline-block;">
	                <input class="easyui-textbox"  id ="searchKeyword" name="searchKeyword"  style="width:100%">
	            </div>
	            
	            <div style="padding:5px 0;display:inline-block;">
		            <a href="javascript:void(0)" id = "search_btn"  style="width:60px;margin-left: 10px;margin-right : 5px">리스트</a>
		            <!--  <a href="javascript:void(0)" id = "search_graph_btn"  style="width:60px;margin-right : 5px;">그래프</a> -->
		            <a href="javascript:void(0)" id = "reset_btn"   style="width:60px">리셋</a>
	       	 	</div>
	       	 	 <div style="padding:0px 0;">
		            <a href="javascript:void(0)" id = "search_year_btn"  style=";margin-left: 10px;">전제 연별 조회</a>
		            <a href="javascript:void(0)" id = "search_month_btn"  style="margin-left: 2px;">전체 월별 조회</a>
	       	 	</div>
	        </form>
    	</div>
	</div>
	
		<div  data-options="region:'center',split:true"  style = "height:40%"> 
		<table id = "summary_table" style ="width:100%;height:100%">
		</table> 
	</div>
	
	<div  data-options="region:'south',split:true"  style = "height:60%"> 
		<table id = "node_list" style ="width:100%;height:100%">
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
