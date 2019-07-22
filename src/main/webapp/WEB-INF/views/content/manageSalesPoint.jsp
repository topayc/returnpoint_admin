<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="tab"  style="width:100%;padding: 10px;">
    <div title="단말기 영업 관리자 포인트 지급" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
        <div class="easyui-panel" title="" style="width:100%;padding:5px 5px;">
	        <ul style ="font-weight:bold;margin-bottom: 30px">
				<li style ="margin-bottom:5px;font-size:12px">지정한 월의 단말기 영업자(투자자)에 대한 포인트를 지급합니다.</li>
				<li style ="margin-bottom:5px;font-size:12px">이미 지급한 날은 지급할 수 없습니다.</li>
				<li style ="margin-bottom:5px;font-size:12px">지급을 위한 가맹점-영업자 연결 파일 : affiliate_sales.properties</li>
				<li style ="margin-bottom:5px;font-size:12px">월별 지급 내역 파일 : affiliate_sales_acc_history.txt</li>
				<li style ="margin-bottom:5px;font-size:12px;color : red">위 파일 수정 혹은 변경은 반드시 관리자에게 문의 해주세요(절대 임의 수정 변경 금지)</li>
			</ul>
	        <form id="searchForm" method="post" >
	             <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <select id = "year" class="easyui-combobox" name="year" label="연도" style="width:100%">
	                	 <option value = "2019" selected="selected">2019</option>
	                </select>	
	            </div>
	            
	             <div style="margin-bottom:10px;margin-left : 10px;display:inline-block;">
	                <select id = "month" class="easyui-combobox" name="month" label="월" style="width:100%">
	                	 <option value = "01">1</option>
	                	 <option value = "02">2</option>
	                	 <option value = "03">3</option>
	                	 <option value = "04">4</option>
	                	 <option value = "05">5</option>
	                	 <option value = "06">6</option>
	                	 <option value = "07">7</option>
	                	 <option value = "08">8</option>
	                	 <option value = "09">9</option>
	                	 <option value = "10">10</option>
	                	 <option value = "11">11</option>
	                	 <option value = "12">12</option>
	                </select>	
	            </div>
	            
	            <div style="margin-bottom:10px;margin-left:7px;;display:inline-block;">
	                <input class="easyui-textbox"  id ="keyword" name="keyword"  style="width:100%">
	            </div>
	            
	            <div style="padding:5px 0;display:inline-block;">
		            <a href="javascript:void(0)" id = "search_btn"  style="width:80px;margin-right : 5px;margin-left:5px">검색</a>
		            <a href="javascript:void(0)" id = "act_point"  style="width:120px;margin-right : 5px;margin-left:5px">포인트 지급</a>
		            <a href="javascript:void(0)" id = "reset_btn"   style="width:80px">리셋</a>
	       	 	</div>
	        </form>
    	</div>
    </div>
<!--     <div title="기능1" data-options="iconCls:'icon-ok'" style="padding:10px;">
    </div>
    <div title="기능2" data-options="iconCls:'icon-ok'">
    </div> -->
</div>
<script src="resources/js/${viewReqName}.js"></script>
