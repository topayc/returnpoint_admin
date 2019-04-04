<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		
<div class="easyui-panel" style ="height:70px" >
	<table id = "gift_card_policy_list" style ="width:100%;height:100%"> </table>
</div>

<div style="margin-bottom: 20px; margin-top: 10px">
	<ul style='font-weight: bold';'>
		<li style="margin-bottom: 5px; font-size: 12px">변경 수정된 정책은 어플리케이션 전반에 적용됩니다.</li>
		<li style="margin-bottom: 5px; font-size: 12px">각 셀을 클릭해서 필요한 항목을 변경 한 후  <span style = "color : red; font-weight : bold">정책 업데이트 </span>버튼을 클릭하세요</li>
		<li style="margin-bottom: 5px; font-size: 12px">판매점 수수료 항목은 각 가맹점 수정페이지에서도 개별 적용할 수 있습니다 </li>
		<li style="margin-bottom: 5px; font-size: 12px">개별 가맹점에 판매점 수수료가 미설정된 경우, 정책의 판매점 수수료가 적용됩니다.</li>
</div>
<div style="padding:5px 0;width:120px;margin-top : 10px;margin-left:25px">
	<a href="javascript:void(0)" id = "update_btn"  >정책 업데이트</a>
</div>
<script src="resources/js/${viewReqName}.js"></script>
