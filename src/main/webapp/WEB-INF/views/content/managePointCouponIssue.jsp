<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div style = "padding: 10px;"  id = "fileTab">
    
	<div title="<strong>영수증 적립 쿠폰 발행</strong>"  style="padding:20px;display : inline-block" id="container"  style="display:none;">
			<div >
				<ul style ='font-weight: bold';'>
					<li style ="margin-bottom:5px;font-size:12px">영수증 매출에 의한 포인트 적립 쿠폰 발행</li>
					<li style ="margin-bottom:5px;font-size:12px">발행 후 포인트 적립 코드 관리에서 발행된 내역을 조회할 수 있습니다.</li>
				<!-- 	<li style ="margin-bottom:5px;font-size:12px">상품권은 모바일과 실물상품권 2가지를 발행할 수 있지만, 혼용할 수 없습니다.</li>
					<li style ="margin-bottom:5px;font-size:12px">상품권은 한번에 <span style = "color: red">50,000</span> 매 까지 일괄 발행이 가능합니다</li>
					<li style ="margin-bottom:5px;font-size:12px">본사 발행의 경우 특수 목적을 위한 선발주 발행으로, 이러한 발행은 입금확인 없이 발행 버튼이 활성화 됩니다.</li> -->
				<!-- 	<li style ="margin-bottom:5px;font-size:12px">상품권 발생 및 관리 절차.
						<ul style ='font-weight: bold';'>
							<li style ="margin-bottom:5px;font-size:12px">상품권 생성</li>
							<li style ="margin-bottom:5px;font-size:12px">상품권 발행 내역 관리 페이지에서 생성 완료 확인</li>
							<li style ="margin-bottom:5px;font-size:12px">입금 완료로 변경하면 요청한 상품권 내역이 생성</li>
						</ul>
					</li> -->
					
				</ul>
			</div>
			<div style = "width:50%;float: left"  >
			<div style = "padding:20px 15px" >
			   <form id="pointCouponCreate"   name = "pointCouponCreate" method="post">
					<div style="margin-bottom:20px;" ><input id ="payAmount"  name="payAmount" style="width:100%;"> </div>
					<div style="margin-bottom:20px;" >
						<select id ="accPointRate"  name="accPointRate" style="width:100%;" >
							<option value= "100">100%</option>
							<option value= "90">90%</option>
							<option value= "80" selected  = "selected" >80%</option>
							<option value= "70">70%</option>
							<option value= "60">60%</option>
							<option value= "50">50%</option>
							<option value= "40">40%</option>
							<option value= "30">30%</option>
							<option value= "20">20%</option>
							<option value= "10">10%</option>
							<option value= "0">0%</option>
						</select> 
					</div>
					<div style="margin-bottom:20px;" ><input id ="accPointAmount"  name="accPointAmount" style="width:100%;"> </div>
					<div style="margin-bottom:20px;" >
						<select id ="accTargetRange"  name="accTargetRange" style="width:100%;" >
							<option value= "1">본인만 적립</option>
							<option value= "2" selected  = "selected">본인 + 1대 </option>
							<option value= "3">본인 + 1대 + 2대 </option>
						</select> 
					</div>
					<div style="margin-bottom:20px;" ><input id ="qty"  name="qty" style="width:100%;" value = "1"> </div>
					<div style="margin-top:10px;padding:5px 0;align: right">
						<a href="javascript:void(0)" id = "create_btn"  style="width:60px;margin-right : 5px">발행</a>
						<a href="javascript:void(0)" id = "reset_btn"   style="width:60px">리셋</a>
					</div>
				</form>
				
			</div>
		</div>
		<div style = "width:50%;float: left" ></div>
	</div>
	<div title="<strong>기타</strong>"  style="padding:20px;display:none;" id="container"   style="display:none;">
	</div>
	
</div>
<script src="resources/js/${viewReqName}.js"></script>
