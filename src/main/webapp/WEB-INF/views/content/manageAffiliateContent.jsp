<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>

.textbox-label{font-weight : 600;font-size : 12px}

.validatebox-readonly{
	background-color : #333;
	color : #fff;
}


</style>
<div class="easyui-layout" data-options="fit:true,border:false,split:true">
	<div  data-options="region:'north',split:true"  id = "member_search"  style ="height:100px" title ="정보 등록 가맹점 검색" >
		 <div class="easyui-panel" title="" style="width:100%;padding:10px 15px;">
	        <form id="searchForm" method="post">
	             <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <select id = "searchAffiliate" class="easyui-combobox" name="searchAffiliate"  style="width:100%">
	                	<option value="0"  >업체 선택</option>
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
	
	<div  data-options="region:'center',split:true,border:true"  > 
		<div class="easyui-layout" data-options="fit:true,split:true,border:false" >
                <div data-options="region:'west',split:true,collapsible:false" style="width:60%;padding: 20px 50px 40px 50px;background-color : #eee" title= "세부 정보 입력">
                	<form id="registerAffiliateDetailForm"  enctype="multipart/form-data" name = "registerAffiliateDetailForm" method="post" >
					  		<div style="margin-bottom:10px;display:inline-block;width: 15%">
	                		<input class="easyui-textbox"  name = "affiliateNo" id = "affiliateNo" style="width:100%" data-options="readonly : true,label:'협력 업체 번호'"  value = "0"/>
					  	</div>
					  	<div style="margin-bottom:10px;display:inline-block;width: 15%">
	                		<input class="easyui-textbox"  name = "affiliateDetailNo" id = "affiliateDetailNo" style="width:100%" data-options="readonly : true,label:'등록번호'"  value = "0"/>
					  	</div>
					  	<div style="margin-bottom:10px;display:inline-block;width: 69%">
					  		<input class="easyui-textbox" id ="buisnessName"  name="buisnessName" style="width:100%" data-options="readonly : true,label:'상호명 '"> 
					  	</div>
					  	<div style="margin-bottom:10px;" >
					  		<input class="easyui-textbox" id ="businessNumber"  name="businessNumber" style="width:100%" data-options="label:'사업자 번호 '"> 
					  		</div>
					  	<div style="margin-bottom:10px;">
					  		<input class="easyui-textbox" id ="businessItem"  name="businessItem" style="width:100%" data-options="label:'업종 ' "> 
					  		</div>
					  	<div style="margin-bottom:10px;">
					  		<input class="easyui-textbox" id ="businessType"  name="businessType" style="width:100%" data-options="label:'업태 '"> 
					  	</div>
					  	
					    <div style="margin-bottom:10px">
					  		<input class="easyui-textbox" id ="holiday"  name="holiday" style="width:100%" data-options="label:'휴무 여부 '"> 
					  		</div>
					  	<div style="margin-bottom:10px">
					  		<input class="easyui-textbox" id ="openingHours"  name="openingHours" style="width:100%" data-options="label:'영업 시간' "> 
					  		</div>
					  	<div style="margin-bottom:10px">
					  		<input class="easyui-textbox" id ="commonWeb"  name="commonWeb" style="width:100%" data-options="label:'웹 사이트 '"> 
					  		</div>
					  	<div style="margin-bottom:10px">
					  		<input class="easyui-textbox" id ="etcLink"  name="etcLink" style="width:100%" data-options="label:'기타 링크'" > 
					  	</div>
					  	
					  	<div style="margin-bottom:10px">
					  		<input class="easyui-textbox" id ="overview"  name="overview" style="width:100%" data-options="label:'소개 '"> 
					  		</div>
					  	<div style="margin-bottom:10px">
					  		<input class="easyui-textbox" id ="afffiliateNotice"  name="afffiliateNotice" style="width:100%" data-options="label:'업체 공지 '"> 
					  	</div>
					  		  	<div style="margin-bottom:10px">
					  		<input class="easyui-textbox" id ="affiliateNews"  name="affiliateNews" style="width:100%" data-options="label:'업체 소식 '"> 
					  	</div>
					  	 <div style="margin-bottom:30px">
					  		<input class="easyui-textbox" id ="etc"  name="etc" style="width:100%" data-options="label:'부가 정보'"> 
					  	</div>

					  	 <div style="margin-bottom:15px">
					  		<input class="easyui-filebox" id ="amImage1"  name="amImage1" style="width:100%" data-options="label:'이미지1 (jpg, png 만 가능)'"> 
					  	</div>
					  	 <div style="margin-bottom:15px">
					  		<input class="easyui-filebox" id ="amImage2"  name="amImage2" style="width:100%" data-options="label:'이미지2 (jpg, png 만 가능)'"> 
					  	</div>
					  	 <div style="margin-bottom:15px">
					  		<input class="easyui-filebox" id ="amImage3"  name="amImage3" style="width:100%" data-options="label:'이미지2 (jpg, png 만 가능)'"> 
					  	</div>

					  	 <div style="margin-bottom:30px">
					  		<input class="easyui-filebox" id ="amImage4"  name="amImage4" style="width:100%" data-options="label:'이미지4 (jpg, png 만 가능)'"> 
					  	</div>
					 
				<div style="padding:0;">
		            <a href="javascript:void(0)" id = "submit_btn"  style="width:100px;">적용하기</a>
	       	 	</div>
					</form>
                
                
                </div>
                <div data-options="region:'center'"  title = "미리보기">
                	<div style = "padding:5px;height:98%;background-color : #ccc">
             		   	<iframe  id = "preview" style ="border : no" width = "100%" height = "100%" ></iframe>
                	</div>
                </div>
            </div>
	</div>
</div>

<script src="resources/js/${viewReqName}.js"></script>
