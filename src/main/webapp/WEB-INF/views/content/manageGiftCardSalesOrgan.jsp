<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="easyui-layout" data-options="fit:true,border:false,split:true">
	<div  data-options="region:'center',split:true,border:false"  > 
		<div class="easyui-layout" data-options="fit:true,border:false" id = "createOrglayout" >
                <!-- <div data-options="region:'south',split:true" style="height:50px"></div>  -->
                <div data-options="region:'center', border:true"> <table id = "data_list" style ="width:100%;height:100%"> </table> </div>
                <div data-options="region:'south',split:true, border:true,fit:true,collapsed : true" style="height:50px;background-color: #ededed" title=" 상품권 판매 조직 생성 / 수정">
                	<div style ="padding:10px;margin: 10px">
						<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >
						   <form id="createGiftCardSalesOrganForm"   name = "createGiftCardSalesOrganForm" method="post"  >
								<input type = "hidden" id = "action" name = "action"  value = "create"/>
								<div style="margin-bottom:16px">
									<select id ="organType" name="organType"   style="width:100%" data-options="label:'조직 구분',labelWidth :100,labelPosition : 'left'">
										<c:forEach var="saleOrganDetailCode"  items="${saleOrganDetailCodes}" varStatus="status">
											    <c:if test = "${ saleOrganDetailCode.useInAdmin == 'Y' }">
												  <option value='${saleOrganDetailCode.key}' >${saleOrganDetailCode.value} </option>
											  </c:if>
										</c:forEach>
									</select> 
								</div>
								<div style="margin-bottom:16px"><input id ="parentNo"  name="parentNo" style="width:100%" data-options="label:'총판 검색',labelWidth :100,labelPosition : 'left'"> </div>
								<div style="margin-bottom:16px"><input id ="parentName"  name="parentName" style="width:100%" data-options="label:'이름',labelWidth :100,labelPosition : 'left'"> </div>
								<input type = "hidden" "id ="giftCardSalesOrganNo"  name="giftCardSalesOrganNo" >
								<div style="margin-bottom:16px;" ><input id ="organCode"  name="organCode" style="width:100%;" data-options="label:'코드 ',labelWidth :100,labelPosition : 'left'"> </div>
								<div style="margin-bottom:16px"><input id ="organPassword"  name="organPassword" style="width:100%" data-options="label:'비밀번호',labelWidth :100,labelPosition : 'left'"> </div>
								<div style="margin-bottom:16px"><input id ="organOwner"  name="organOwner" style="width:100%" data-options="label:'사업주 ',labelWidth :100,labelPosition : 'left'"> </div>
								<div style="margin-bottom:16px"><input id ="organName"  name="organName" style="width:100%" data-options="label:'조직 이름 ',labelWidth :100,labelPosition : 'left'"> </div>
								<div style="margin-bottom:16px"><input id ="organBusinessNumber"  name="organBusinessNumber" style="width:100%" data-options="label:'사업자 번호 ',labelWidth :100,labelPosition : 'left'"> </div>
								<div style="margin-bottom:16px">
									<select id ="organStatus" name="organStatus"   style="width:100%" data-options="label:'조직 상태',labelWidth :100,labelPosition : 'left'">
										<c:forEach var="saleOrganStatus"  items="${saleOrganStatusList}" varStatus="status">
											    <c:if test = "${ saleOrganStatus.useInAdmin == 'Y' }">
												  <option value='${saleOrganStatus.key}' >${saleOrganStatus.value} </option>
											  </c:if>
										</c:forEach>
									</select> 
								</div>
								
								<div style="margin-bottom:16px"><input id ="organEmail"  name="organEmail" style="width:100%" data-options="label:'이메일',labelWidth :100,labelPosition : 'left'"> </div>
								<div style="margin-bottom:16px"><input id ="organPhone"  name="organPhone" style="width:100%" data-options="label:'핸드폰',labelWidth :100,labelPosition : 'left'"> </div>
								<div style="margin-bottom:16px"><input id ="organTel"  name="organTel" style="width:100%" data-options="label:'전화번호',labelWidth :100,labelPosition : 'left'"> </div>
								<div style="margin-bottom:16px"><input id ="organAddr"  name="organAddr" style="width:100%" data-options="label:'주소',labelWidth :100,labelPosition : 'left'"> </div>
								<div style="margin-bottom:16px"><input id ="organBankName"  name="organBankName" style="width:100%" data-options="label:'은행명',labelWidth :100,labelPosition : 'left'"> </div>
								<div style="margin-bottom:16px"><input id ="organBankAccount"  name="organBankAccount" style="width:100%" data-options="label:'계좌번호',labelWidth :100,labelPosition : 'left'"> </div>
								<div style="margin-bottom:16px"><input id ="organBankAccountOwner"  name="organBankAccountOwner" style="width:100%" data-options="label:'계좌 소유주',labelWidth :100,labelPosition : 'left'"> </div>
								
							    <div style="padding:5px 0;display:inline-block;">
						            <a href="javascript:void(0)" id = "create_btn"  style="width:80px;">확인</a>
						            <a href="javascript:void(0)" id = "reset_create_btn"   style="width:80px">초기화</a>
					       	 	</div>
							</form>
						</div>
						<div id="viewImage"></div>
					</div>
                </div>
            </div>
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
	             <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <select id = "searchSalesOrganType" class="easyui-combobox" name="searchSalesOrganType" label="조직 구분 " style="width:100%">
	                	 <option value="0">전체</option>
	                	<c:forEach var="saleOrganDetailCode" items="${saleOrganDetailCodes}" varStatus="status">
						   <option value="${saleOrganDetailCode.key}" >${saleOrganDetailCode.value}</option>
						</c:forEach>
	                </select>
	            </div>
	             <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <select id = "searchSalesOrganStatus" class="easyui-combobox" name="searchSalesOrganStatus" label="조직 상태" style="width:100%">
	                	 <option value="0">전체</option>
	                	<c:forEach var="saleOrganStatus" items="${saleOrganStatusList}" varStatus="status">
						   <option value="${saleOrganStatus.key}" >${saleOrganStatus.value}</option>
						</c:forEach>
	                </select>
	            </div>
	            
	        <%--      <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <select id = "searchKeywordType" class="easyui-combobox" name="searchKeywordType" label="검색 유형" style="width:100%">
	                	 <option value="0">전체</option>
	                	<c:forEach var="keywordType" items="${keywordTypeList}" varStatus="status">
						   <option value="${keywordType.key}" <c:if test="${keywordType.key == nodeSearch.searcheywordType}">selected="selected"</c:if> >${keywordType.value}</option>
						</c:forEach>
	                </select>
	            </div> --%>
	            <div style = "margin-left : 10px;display:inline-block;">
	            <div style="margin-bottom:10px;display:inline-block;">
	                <input class="easyui-textbox"  id ="searchKeyword" name="searchKeyword"  style="width:100%">
	            </div>
	            
	            <div style="padding:5px 0;display:inline-block;">
		            <a href="javascript:void(0)" id = "search_btn"  style="width:80px;margin-right : 5px;margin-left:10px">검색</a>
		            <a href="javascript:void(0)" id = "reset_btn"   style="width:80px">리셋</a>
	       	 	</div>
	       	 	</div>
	        </form>
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
