<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="easyui-layout" data-options="fit:true,border:false,split:true">
	<div  data-options="region:'center',split:true"  > 
		<table id = "node_list" style ="width:100%;height:100%">
		</table> 
	</div>
	<div  data-options="region:'north',split:true"  id = "member_search"  style ="height:110px" title ="검색 조건" >
		 <div class="easyui-panel" title="" style="width:100%;padding:10px 15px;">
	        <form id="searchForm" method="post">
	            <div style="margin-bottom:10px;margin-left : 10px;display:inline-block">
	                <input class="easyui-datetimebox"   id = "searchDateStart" name="searchDateStart" label="검색 시작일" style="width:100%"/>
	            </div>
	            
	            <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <input class="easyui-datetimebox" id = "searchDateEnd"  name="searchDateEnd" label="검색 종료일" style="width:100%"/>
	            </div>
	             
	             <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <select id = searchNodeType class="easyui-combobox" name="searchNodeType" label="검색(생성) 그룹" style="width:100%">
	                	<c:forEach var="nodeType" items="${nodeTypeList}" varStatus="status">
						   <option value="${nodeType.key}" <c:if test="${nodeType.key == searchCondition.searchNodeType}">selected="selected"</c:if>><strong>${nodeType.value}</strong></option>
						</c:forEach>
	                </select>
	            </div>
	            
	             <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <select id = "searchNodeStatus" class="easyui-combobox" name="searchNodeStatus" label="상태" style="width:100%">
	                	 <option value="0">전체</option>
	                	<c:forEach var="statusType" items="${nodeStatusList}" varStatus="status">
						   <option value="${statusType.key}" <c:if test="${statusType.key == nodeSearch.searchodeStatus}">selected="selected"</c:if> >${statusType.value}</option>
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
	            
	            <div style="margin-bottom:10px;margin-left:7px;;display:inline-block;">
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


<div id="bank_add_dlg" style = "padding : 15px;display : none">
  <div style="margin-top:10px;margin-bottom:20px;margin-left : 10px;">
		<input  id = "memberBankAccountNo"  name="memberBankAccountNo" style="width:100%"/>
	</div>
	
  <div style="margin-bottom:20px;margin-left : 10px;">
		<input  id = "bankName"  name="bankName" style="width:100%"/>
	</div>
  <div style="margin-bottom:20px;margin-left : 10px;">
		<input  id = "accountOwner"  name="accountOwner"  style="width:100%"/>
	</div>
  <div style="margin-bottom:10px;margin-left : 10px;">
		<input  id = "bankAccount"  name="bankAccount"  style="width:100%"/>
	</div>
</div>

<div id="bank_list_dlg" style = "padding : 15px;display : none">
   <table id = "bank_list_table" style ="width:100%;height:100%"></table> 
</div>



<div id="add_affiliate_tid_virew" style = "width: 40% ;padding : 15px;display : none">
	<div>
			<ul style ='font-weight: bold';'>
				<li style ="margin-bottom:5px;font-size:12px">선택한 협력업체의 타입은 <span id = "aff_type" style = "color : blue">무사업자</span>입니다</li>
				<li style ="margin-bottom:5px;font-size:12px">무사업자의 경우, TID는 직접 생성할 수 없으며, 생성 버튼을 눌러 생성하세요</li>
				<li style ="margin-bottom:5px;font-size:12px">무사업자의 경우 다른 타입의 협력업체 타입을 추가할 수 없습니다</li>
			</ul>
		</div>
   	<div style = "margin-left : 30px; margin-top : 20px">
   		<input id ="add_tid"  name="tid" >
   		<a id = "gen_tid"  style = "margin-left: 10px">TID 생성</a>   
   		</div>
</div>

<div id="biz_info_dlg" style = "padding : 20px;display : none">
	<div style = "margin-bottom: 20px">
		<ul style ='font-weight: bold';'>
			<li style ="margin-bottom:5px;font-size:12px">아래의 사업정보를 입력해주세요</li>
		</ul>
	</div>
   	<form id = "biz_info_form">
   	<div style="margin-bottom:12px">
		<input id ="businessNumber"  name="businessNumber" style="width:100%" data-options="labelWidth :100,labelPosition : 'left'"> 
	</div>
   	<div style="margin-bottom:12px">
		<input id ="businessType"  name="businessType" style="width:100%" data-options="labelWidth :100,labelPosition : 'left'"> 
	</div>
   	<div style="margin-bottom:12px">
		<input id ="businessItem"  name="businessItem" style="width:100%" data-options="labelWidth :100,labelPosition : 'left'"> 
	</div>
	</form>
</div>

<div id="affiliate_tag_dlg" style = "padding : 20px;display : none">
	<div style = "margin-bottom: 20px">
		<ul style ='font-weight: bold';'>
			<li style ="margin-bottom:5px;font-size:12px">가맹점 카테고리 정보외에 기타 업종 세분 혹은 특이 정보를 입력해주세요</li>
			<li style ="margin-bottom:5px;font-size:12px">가맹점 태그는 복수 입력이 가능합니다.</li>
			<li style ="margin-bottom:5px;font-size:12px;font-weight:bold; color : red">가맹점 태그는 복수 태그 입력시 쉼표(,) 로 구분해주세요</li>
		</ul>
	</div>
   	<form id = "affiliate_tag_form">
   	<div style="margin:20px;margin-top:40px">
		<input id ="affiliateTag"  name="affiliateTag" style="width:100%" data-options="labelWidth :50,labelPosition : 'left'"> 
	</div>
	</form>
</div>

<div id="dlg-buttons">
        <a href="javascript:void(0)"  id = "add_tid_btn" class="easyui-linkbutton">확인</a>
        <a href="javascript:void(0)" id  = "cancel_btn" class="cancel_btn" >취소</a>
    </div>

<div id="createPaymentRouterFormDlg" style = "padding : 15px;display : none">
	<div style="margin-bottom: 20px;">
		<p>가맹점별 하나의  결제 라우팅을 생성할 수 있습니다</p>
	</div>
	<div style="margin-bottom: 20px; margin-left: 10px;">
		<select id = "paymentRouterType"  name="paymentRouterType" label="결제 라우터 타입" style="width:100%">
	     <c:forEach var="paymentRouterType" items="${paymentRouterTypes}" varStatus="status">
			<option value="${paymentRouterType.value}"
				<c:if test="${paymentRouterType.value == 'VAN'}">selected="selected"</c:if>><strong>${paymentRouterType.value}</strong></option>
		</c:forEach>
		</select>
	</div>

	<div style="margin-bottom: 10px; margin-left: 10px;">
		<select id = paymentRouter  name="paymentRouter" label="검색(생성) 그룹" style="width:100%">
		</select>
	</div>

</div>

<div id="view_affiliate_tids" style = "padding : 15px;display : none">
   <table id = "tid_list" style ="width:100%;height:100%"></table> 
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
