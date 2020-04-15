<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="easyui-layout" data-options="fit:true,border:false,split:true">
	<div  data-options="region:'center',split:true,border:false"  > 
		<div class="easyui-layout" data-options="fit:true,border:false" id = "createProdcutLayout" >
                <!-- <div data-options="region:'south',split:true" style="height:50px"></div>  -->
                <div data-options="region:'center', border:true"> <table id = "data_list" style ="width:100%;height:100%"> </table> </div>
                <div data-options="region:'south',split:true, border:true,fit:true,collapsed : true" style="height:50px;background-color: #ededed" title=" 캐피탈 프로그램 생성/수정">
                	<div style ="padding:10px;margin: 10px">
						<div style = "padding:10px;padding-top:5px;padding-bottom:5px" >
						   <form id="createProdcutForm"   name = "createProdcutForm" method="post"  >
								<input type = "hidden" id = "action" name = "action"  value = "create"/>
								<div style="margin-bottom:16px">
									<div style="margin-bottom:16px">
										<span style = "font-weight : 700"> * 아래의 항목은 모두 필수 입력 항목입니다.</span>
									</div>
								</div>
								<div style="margin-bottom:16px">
									<input id ="capitalProductName"  name="capitalProductName" style="width:100%" > </div>
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
	             <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <select id = "searchSalesOrganType" class="easyui-combobox" name="searchSalesOrganType" >
	                	 <option value="0">전체</option>
	                	<option>만기도래전</option>
	                	<option>만기도래</option>
	                </select>
	            </div>
	            
	              <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <select id = "searchCapitalWithdrawalDate" class="easyui-combobox" name="searchCapitalWithdrawalDate" label="출금일별 " style="width:100%">
	                	 <option value="0">전체</option>
	                	<option>1일</option>
	                	<option>5일</option>
	                </select>
	            </div>
	            
	            <div style = "margin-left : 10px;display:inline-block;">
	            <div style="margin-bottom:10px;display:inline-block;">
	                <input class="easyui-textbox"  id ="searchKeyword" name="searchKeyword"  style="width:100%">
	            </div>
	            
	            <div style="padding:5px 0;display:inline-block;">
		            <a href="javascript:void(0)" id = "search_btn"  style="margin-left:10px">검색</a>
		            <a href="javascript:void(0)" id = "reset_btn"   >리셋</a>
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
