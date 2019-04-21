<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="easyui-layout" data-options="fit:true,border:false,split:true">
	<div  data-options="region:'center',split:true"  > 
		<table id = "gift_card_issue_list" style ="width:100%;height:100%">
		</table> 
	</div>
	<div  data-options="region:'north',split:true"  id = "member_search"  style ="height:110px" title ="검색 조건" >
		 <div class="easyui-panel" title="" style="width:100%;padding:5px 5px;">
	        <form id="searchForm" method="post">
	            <div style="margin-bottom:10px;margin-left : 10px;display:inline-block">
	                <input class="easyui-datetimebox"   id = "searchDateStart" name="searchDateStart" label="검색 시작일" style="width:100%"/>
	            </div>
	            
	            <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <input class="easyui-datetimebox" id = "searchDateEnd"  name="searchDateEnd" label="검색 종료일" style="width:100%"/>
	            </div>
	             <%-- 
	             <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <select id = "searchProductCategory" class="easyui-combobox" name="searchProductCategory" label="상품 카테고리" style="width:100%">
	                	<c:forEach var="nodeType" items="${nodeTypeList}" varStatus="status">
						</c:forEach>
	                </select>
	            </div> --%>
	            
	             <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <select id = "searchProductStatus" class="easyui-combobox" name="searchProductStatus" label="상품 상태" style="width:100%">
	                	 <option value="0">전체</option>
	                	<c:forEach var="productStatus" items="${productStatusList}" varStatus="status">
						   <option value="${productStatus.key}" >${productStatus.value}</option>
						</c:forEach>
	                </select>
	            </div>
	            
	             <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <select id = "searchKeywordType" class="easyui-combobox" name="searchKeywordType" label="검색 유형" style="width:100%">
	                	 <option value="0">전체</option>
	                	<c:forEach var="keywordType" items="${keywordTypeList}" varStatus="status">
						   <option value="${keywordType.key}" <c:if test="${keywordType.key == nodeSearch.searcheywordType}">selected="selected"</c:if> >${keywordType.value}</option>
						</c:forEach>
	                </select>
	            </div>
	            
	            <div style="margin-bottom:10px;margin-left:7px;display:inline-block;">
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
<div  id = "more_detail_view"  style = "display:none;"> 
		<table id = "issue_overview" style ="width:100%;height : 100%;" > </table>
		<!-- <table id = "order_item_list"  style ="width:100%;height : 50%; "></table> -->
</div>
<div id="send_gift_sms_view" style = "padding : 30px">
   	<select id = "receiverPhone1" style="width:20%" name = "receiverPhone1">
		<option value="010">010</option>
		<option value="011">011</option>
	</select>&nbsp;-&nbsp;
   	<input id ="receiverPhone2"  name="receiverPhone2" style="width:30%;margin-left : 20px"> -  
   	<input id ="receiverPhone3"  name="receiverPhone3" style="width:30%;margin-left : 20x">
</div>

<div id="qr_code_view" class="easyui-dialog" title="QR Code" style="width:550px;height:600px;">
    <img id = "qr_code_no"  width = "500" height = "500" src = ""/>
</div>

  <div id="dlg-buttons">
        <a href="javascript:void(0)"  id = "sendGiftCardByMobile" class="easyui-linkbutton" onclick="javascript:sendGiftCardByMobile()">상품권 전송</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#send_gift_sms_view').dialog('close')">취소</a>
    </div>

<script src="resources/js/${viewReqName}.js"></script>
