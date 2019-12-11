<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="easyui-layout" data-options="fit:true,border:false,split:true">
	<div  data-options="region:'north',split:true"  id = "member_search"  style ="height:150px" title ="검색 조건" >
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
	                <select id = "searchUseStatus" class="easyui-combobox" name="searchUseStatus" label="코드 사용 상태" style="width:100%">
	                	 	<option value="0">전체</option>
						   <option value="1">사용 가능</option>
						   <option value="2">사용 중지</option>
						   <option value="3">사용 완료</option>
						   <option value="4">등록 취소 </option>
	                </select>
	            </div>
	            
   				<div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <input class="easyui-textbox"  id ="searchKeyword" name="searchKeyword"  style="width:100%" label="이름/아이디">
	            </div>
	            
	             <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
		            <a href="javascript:void(0)" id = "search_btn"  style="width:60px;margin-left: 10px;">검색</a>
		            <a href="javascript:void(0)" id = "reset_btn"   style="width:60px;margin-left: 2px;">리셋</a>
	       	 	</div>
	       	 	
	            <div style="padding:5px 0; ">
		                
		              <a href="javascript:void(0)" id = "search_total_daily_btn"  style=";margin-left: 15px;">전체 일별 조회</a>
		            <a href="javascript:void(0)" id = "search_total_month_btn"  style="margin-left: 3px;">전체 월별 조회</a>
		            <a href="javascript:void(0)" id = "search_total_year_btn"  style=";margin-left: 3px;">전체 연별 조회</a>
		            <!-- <a href="javascript:void(0)" id = "search_graph_btn"  style="margin-left: 2px;">그래프 보기</a> -->
	       	 	</div>
	        </form>
    	</div>
	</div>
	
		<div  data-options="region:'center',split:true"  style = "height:50%"> 
		<table id = "summary_table" style ="width:100%;height:100%">
		</table> 
	</div>
	
	<div  data-options="region:'south',split:true"  style = "height:50%"> 
		<table id = "node_list" style ="width:100%;height:100%">
		</table> 
	</div>
</div>
	<div id = "pointback_record_dlg" style ="padding:5px;background-color : rgb(237, 237, 237)" class="modal_dialog" > 
		</br><table id = "pointback_record_list" style ="width:100%;height:100%"></table>
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
