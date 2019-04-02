<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div style = "padding: 10px;"  id = "fileTab">
    
	<div title="<strong>상품권 발행</strong>"  style="padding:20px;display : inline-block" id="container"  style="display:none;">
			<div >
				<ul style ='font-weight: bold';'>
					<li style ="margin-bottom:5px;font-size:12px">상품원 상품 관리 페이지에서 상품권을 등록할 수 있습니다</li>
					<li style ="margin-bottom:5px;font-size:12px">상품권은 모바일과 실물상품권 2가지를 발행할 수 있지만, 혼용할 수 없습니다.</li>
					<li style ="margin-bottom:5px;font-size:12px">상품권은 한번에 <span style = "color: red">50,000</span> 매 까지 일괄 발행이 가능합니다</li>
					<li style ="margin-bottom:5px;font-size:12px">본사 발행의 경우 특수 목적을 위한 선발주 발행으로, 이러한 발행은 입금확인 없이 발행 버튼이 활성화 됩니다.</li>
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
			   <form id="giftCardIssueForm"   name = "giftCardIssueForm"  enctype="multipart/form-data" method="post">
					<div style="margin-bottom:20px;" >
						<select id ="giftCardOrderType"  name="giftCardOrderType" style="width:100%;" >
							<option value= "10">본사</option>
							<option value= "12">판매점</option>
						</select> 
					</div>
					<input type = "hidden" id ="giftCardSalesOrganNo"  name="giftCardSalesOrganNo"  > 
					<div style="margin-bottom:20px;;" ><input id ="giftCardSalesOrganName"  name="giftCardSalesOrganName" style="width:100%;" > </div>
					<div style="margin-bottom:20px;;" ><input id ="giftCardSalesOrganCode"  name="giftCardSalesOrganCode" style="width:100%;" > </div>
					
					<div style="margin-bottom:20px;">
						<select id ="giftCardNo" name="giftCardNo"   style="width:100%" >
							<c:forEach var="giftCard"  items="${giftCards}" varStatus="status">
								  <c:if test = "${ giftCard.giftCardSaleStatus == '1' }">
									  <option value='${giftCard.giftCardNo}:${giftCard.giftCardSalePrice}' >${giftCard.giftCardName} - <fmt:formatNumber value="${giftCard.giftCardSalePrice}" pattern="###,###,###,###"/> 원 상품권</option>
								  </c:if>
							</c:forEach>
						</select>
					</div>
					<div style="margin-bottom:20px;;" >
						<select id ="giftCardType"  name="giftCardType" style="width:100%;" >
							<c:forEach var="giftCardType"  items="${giftCardTypes}" varStatus="status">
								  <c:if test = "${ giftCardType.useInAdmin == 'Y' }">
									  <option value='${giftCardType.key}' >${giftCardType.value}</option>
								  </c:if>
							</c:forEach>
						</select> 
					</div>
					<input type = "hidden" name = "orderReason" id = "orderReason"/>
					<div style="margin-bottom:20px;" ><input id ="qty"  name="qty" style="width:100%;"> </div>
					<div style ="margin-bottom:10px;margin-top:10px;width : 600px;padding : 20px;background-color : #eeeeee"    class = "easyui-panel">
						<span style = "font-size : 16px; font-weight : 700;">총 발행 금액은 <span id = "total_price" style = "font-size : 16px; font-weight : 700;color : #F92F0C">  0 </span>&nbsp;원입니다</span>
					</div>
					<div style="margin-top:10px;padding:5px 0;align: right">
						<a href="javascript:void(0)" id = "create_btn"  style="width:60px;margin-right : 5px">발행</a>
						<a href="javascript:void(0)" id = "reset_btn"   style="width:60px">취소</a>
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
