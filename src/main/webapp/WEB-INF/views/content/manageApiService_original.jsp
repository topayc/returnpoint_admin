<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="easyui-layout" data-options="fit:true,border:false,split:true">
	<div  data-options="region:'center',split:true"  > 
		<table id = "api_service_list" style ="width:100%;height:100%">
		</table> 
	</div>
	<div  data-options="region:'north',split:true"  id = "member_search"  style ="height:170px" title ="검색 조건" >
		 <div class="easyui-panel" title="" style="width:100%;padding:10px 15px;">
	        <form id="searchForm" method="post">
	             <div style="margin-bottom:10px;margin-left : 10px;width:200px;display:inline-block">
	                <input class="easyui-datetimebox"  id = "searchDateStart" name="searchDateStart" label="검색 시작일" style="width:100%"/>
	            </div>
	            
	            <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;width:200px">
	                <input class="easyui-datetimebox" id = "searchDateEnd"  name="searchDateEnd" label="검색 종료일" style="width:100%"/>
	            </div>
	             
	             <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;width:200px">
	                <select id = "searchApiServiceType" class="easyui-combobox" name="searchApiServiceType" label="검색(생성) 그룹" style="width:100%">
	                	<option value="">전체</option>
	                	<c:forEach var="apiService" items="${apiServiceList}" varStatus="status">
						   <option value="${apiService.key}" <c:if test="${apiService.key == searchCondition.searchApiServiceType}">selected="selected"</c:if>>${apiService.value}</option>
						</c:forEach>
	                </select>
	            </div>
	            
	             <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;width:200px">
	                <select id = "searchApiServiceStatus" class="easyui-combobox" name="searchApiServiceStatus" label="상태" style="width:100%">
	                	 <option value="">전체</option>
	                	<c:forEach var="statusType" items="${apiServiceStatusList}" varStatus="status">
						   <option value="${statusType.key}" <c:if test="${statusType.key == apiServiceSearch.searchApiServiceStatus}">selected="selected"</c:if> >${statusType.value}</option>
						</c:forEach>
	                </select>
	            </div>
	             
	            <div style="margin-bottom:10px;margin-left:7px;width:630px;display:inline-block;">
	                <input class="easyui-textbox"  id ="searchKeyword" name="searchKeyword"  style="width:100%">
	            </div>
	            
	            <div style="padding:5px 0;display:inline-block;">
		            <a href="javascript:void(0)" id = "search_btn"  style="width:80px;margin-right : 5px;margin-left:10px">검색</a>
		            <a href="javascript:void(0)" id = "reset_btn"   style="width:80px">리셋</a>
	       	 	</div>
	        </form>
	        
    	</div>
	</div>
	
</div>

	

<script src="resources/js/${viewReqName}.js"></script>
