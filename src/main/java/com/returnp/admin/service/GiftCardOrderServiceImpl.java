package com.returnp.admin.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.AppConstants;
import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.common.ReturnpException;
import com.returnp.admin.dao.mapper.GiftCardOrderMapper;
import com.returnp.admin.dao.mapper.GiftCardPolicyMapper;
import com.returnp.admin.dao.mapper.GreenPointMapper;
import com.returnp.admin.dao.mapper.PointBackMapper;
import com.returnp.admin.dto.GiftCardOrderForm;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCard;
import com.returnp.admin.model.GiftCardOrder;
import com.returnp.admin.model.GiftCardPolicy;
import com.returnp.admin.model.GiftCardSalesOrgan;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.Policy;
import com.returnp.admin.service.interfaces.GiftCardOrderItemService;
import com.returnp.admin.service.interfaces.GiftCardOrderService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class GiftCardOrderServiceImpl implements GiftCardOrderService{

	@Autowired GiftCardOrderMapper giftCardOrderMapper;
	@Autowired SearchService searchService;;
	@Autowired GiftCardOrderItemService  orderItemService;
	@Autowired GiftCardPolicyMapper giftCardPolicyMapper;
	@Autowired PointBackMapper pointBackMapper;
	@Autowired GreenPointMapper greenPointMapper;
	
	@Override
	public ReturnpBaseResponse createGiftCardOrder(GiftCardOrderForm orderForm) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			GiftCardOrder order  = new GiftCardOrder();
			GiftCard giftCardProduct = new GiftCard();
			giftCardProduct.setGiftCardNo(orderForm.getGiftCardNo());
			
			/* 선택한 상품번에 대한 정보 */
			ArrayList<GiftCard> giftCardProducts  = this.searchService.selectGiftCards(giftCardProduct); 
			if (giftCardProducts == null ||  giftCardProducts.size() != 1 ) {
				ResponseUtil.setSuccessResponse(res, "301" , "잘못된 상품 번호 입니다.");
				throw new ReturnpException(res);
			}

			/* 선택한 구매 조직에 대한 정보 */
			GiftCardSalesOrgan organ = new GiftCardSalesOrgan();
			organ.setOrganCode(orderForm.getGiftCardSalesOrganCode());
			ArrayList<GiftCardSalesOrgan> saleOrgans = this.searchService.selectGiftCardSalesOrgans(organ); 
			if (saleOrgans == null ||  saleOrgans.size() != 1 ) {
				ResponseUtil.setSuccessResponse(res, "301" , "잘못된 구매 조직 번호 입니다.");
				throw new ReturnpException(res);
			}

			giftCardProduct = giftCardProducts.get(0);
			organ = saleOrgans.get(0);
			
			/*주문 내역 저장*/
			order.setOrdererName(orderForm.getGiftCardSalesOrganName());
			order.setOrdererPhone(organ.getOrganPhone());
			order.setOrdererId(orderForm.getGiftCardSalesOrganCode());
			order.setOrdererEmail(organ.getOrganEmail());
			order.setOrderTotalPrice(giftCardProduct.getGiftCardSalePrice() * orderForm.getQty());
			order.setOrderType(orderForm.getGiftCardOrderType());
			order.setOrderStatus(AppConstants.OrderStatus.ORDER_RECEPTION);
			order.setIssueStatus(AppConstants.IssueStatus.PREPARE_TO_ISSUE);
			order.setBargainType(orderForm.getGiftCardOrderType().equals("10") ? AppConstants.BargainType.CREDIT : AppConstants.BargainType.COMMON);
			order.setOrderReason(orderForm.getOrderReason());
			order.setGiftCardNo(orderForm.getGiftCardNo());
			order.setGiftCardName(giftCardProduct.getGiftCardName());
			order.setGiftCardType(orderForm.getGiftCardType());
			order.setGiftCardAmount(giftCardProduct.getGiftCardAmount());
			order.setGiftCardSalePrice(giftCardProduct.getGiftCardSalePrice());
			order.setQty(orderForm.getQty());
			order.setOrderTime(new Date());
			order.setReceiverName(orderForm.getGiftCardSalesOrganName());
			order.setReceiverEmail(organ.getOrganEmail());
			order.setReceiverPhone(organ.getOrganPhone());
			
			order.setPaymentStatus(AppConstants.PaymentStatus.PAYMENT_CHECK);
			order.setPaymentType(AppConstants.PaymentType.PAYMENT_ONLINE);

			order.setDeliveryAddress(organ.getOrganAddr());
			order.setDeliveryMessage(null);
			order.setDeliveryNumber(null);
			
			int affectedRow1 = this.giftCardOrderMapper.insert(order);
			if (affectedRow1 != 1) throw new Exception();
			
			/*
			 * 인서트 시 생성된 orderNo 를 바탕으로 주문 명과 주문 번호 생성 후 다시 업데이트
			 * */
			
			SimpleDateFormat format = new SimpleDateFormat("yyMM");
			Date d = new Date();
			/*주문 번호 생성*/
			String orderNumber = String.format("%s%s%s%08d", orderForm.getGiftCardOrderType(), orderForm.getGiftCardType(), format.format(d), order.getOrderNo());
			order.setOrderNumber(orderNumber);
			/*주문명 생성*/
			order.setOrderName(String.format("%s_%s",orderForm.getGiftCardSalesOrganName(), orderNumber));
			int affectedRow2 =  this.giftCardOrderMapper.updateByPrimaryKeySelective(order);
			if (affectedRow2 != 1) throw new Exception();
			/*
			 * 해당 주문에 속해있는 주문 아이템 생성 및 저장
			 * 하나의 주문에 하나의 주문 상품만 담기는 것으로 변경했기 때문에 아래 코드는 사용하지 않음
			 * */
		/*	GiftCardOrderItem orderItem = new GiftCardOrderItem();
			orderItem.setOrderNumber("9999999");
			orderItem.setProductNo(giftCardProduct.getProductNo());
			orderItem.setQty(orderForm.getQty());
			orderItem.setTotalPrice(giftCardProduct.getProductPrice() * orderForm.getQty());
			this.orderItemService.createGiftCardOrderItem(orderItem);*/
			
			ResponseUtil.setSuccessResponse(res, "100" , "상품권 주문 생성완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품권 주문  생성 에러");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse updateGiftCardOrder(GiftCardOrder order) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			int affectedRow = this.giftCardOrderMapper.updateByPrimaryKeySelective(order);
			if (affectedRow != 1) {
				throw new Exception();
			}
			String resultMesage  = null;
			switch(order.getPaymentStatus()) {
			case "1" :
				resultMesage = "상품권 주문 수정 완료";
				break;
			
			/* 결제 확인시 상품권 본사에 정책에 설정된 퍼센티지의  포인트 지급 */
			case "2" : 
				/* 정책 조회 */
				Policy defaultPolicy = new Policy();
				ArrayList<Policy> policies = this.pointBackMapper.findPolicies(defaultPolicy);
				defaultPolicy = policies.get(policies.size() -1 );
				
				/*해당 상품권 오더 조회*/
				GiftCardOrder giftCardOrder = this.giftCardOrderMapper.selectByPrimaryKey(order.getOrderNo());
				if (giftCardOrder == null) {
					ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "789", "상품권 주문 수정 (결제 확인 )에러");
					throw new ReturnpException(res);
				}
				
				/* 정책상에 정해진 판매 수수료를 포인트로 받기 위한 대상 아이디 조회 - 상품권 정책 조회 */
				GiftCardPolicy giftCardPolicy = this.giftCardPolicyMapper.selectByPrimaryKey(1);
				if (giftCardPolicy == null) {
					ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "791", "상품권 주문 수정 (결제 확인 )에러");
					throw new ReturnpException(res);
				}
				
				String accTargetEmail = giftCardPolicy.getSalesCommissionTarget();
				if (org.apache.commons.lang3.StringUtils.isBlank(accTargetEmail)) {
					ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "793", "에러 - 해당 상품권 주문의 포인트를 받을 대상이 정해지지 않았습니다. </br> 상품권 정책에서 대상을 설정하세요");
					throw new ReturnpException(res);
				}
				
				/*판매수수료를 받을 회원 검색 */
				Member member = new Member();
				member.setMemberEmail(accTargetEmail);
				ArrayList<Member> members = this.searchService.findMembers(member);
				if (members.size() != 1) {
					ResponseUtil.setResponse(
						res, 
						ResponseUtil.RESPONSE_ERROR, 
						"789", 
						"상품권 정책의 포인트 적립 대상   " +accTargetEmail  + "에 대한 회원정보가 존재하지 않습니다.</br>상품권 정책에서 해당 대상을 확인하세요");
					throw new ReturnpException(res);
				}
			 	
				if (!"Y".equals(members.get(0).getIsAffiliate())) {
					ResponseUtil.setResponse(
						res, 
						ResponseUtil.RESPONSE_ERROR, 
						"799", 
						"상품권 정책의 포인트 적립 대상   " +accTargetEmail  + "협력업체 권한이 없습니다. 해당 회원을 협력업체로 등록이 필요합니다.");
					throw new ReturnpException(res);
				}
				/*상품권 오더 판매 수수료를 지정된 타켓으로  지정된 퍼센티지로 포인트를 적립 */
			      this.increaseGiftCardPoint(
			    	  members.get(0).getMemberNo(), 
			    	  members.get(0).getMemberNo(), 
			    	  AppConstants.NodeType.AFFILIATE,
			    	  "affiliate",  
			    	  giftCardOrder.getOrderTotalPrice(),
			    	  giftCardPolicy.getSalesCommissionRate()
			      );
			  	resultMesage = "상품권이 결제 완료상태로 변경되었으며, 본사에 " + giftCardPolicy.getSalesCommissionRate() * 100;
				break;
			case "3" :
				break;
			case "4" :
				break;
			case "5" :
				break;
			}
			ResponseUtil.setResponse(res, "100" , "상품권 주문 수정 완료");
			return res;
		}catch(ReturnpException e) {
			e.printStackTrace();
			if (!TransactionAspectSupport.currentTransactionStatus().isRollbackOnly()) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
			res = e.getBaseResponse();
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품권 주문 수정 에러");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse deleteGiftCardOrder(GiftCardOrder order) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			int affectedRow = this.giftCardOrderMapper.updateByPrimaryKey(order);
			if (affectedRow != 1) {
				throw new Exception();
			}
			ResponseUtil.setSuccessResponse(res, "100" , "상품권 주문 삭제 완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품권 주문 삭제 에러");
			return res;
		}
	}

	@Override
	public void increaseGiftCardPoint(int memberNo, int nodeNo, String nodeType, String nodeTypeName, int amount,
			float giftCardAccRate) throws ReturnpException {
		try {
			/* G Point 증가 업데이트 */
			GreenPoint point =  new GreenPoint();
			ArrayList<GreenPoint> greenPoints = null;

			point.setMemberNo(memberNo);
			point.setNodeType(nodeType);

			if (nodeType.equals(AppConstants.NodeType.RECOMMENDER)) {
				greenPoints = this.pointBackMapper.findGreenPoints(point);
				point = greenPoints == null || greenPoints.size() < 1 ? this.createRecommenderRPoint(memberNo) : greenPoints.get(0);
			}else {
				point = this.pointBackMapper.findGreenPoints(point).get(0);
			}

			point.setNodeNo(nodeNo);
			point.setNodeTypeName(nodeTypeName);
			point.setPointAmount(point.getPointAmount() + ( amount * giftCardAccRate));
			this.greenPointMapper.updateByPrimaryKey(point);
			
		} catch (Exception e) {
			e.printStackTrace();
			ReturnpBaseResponse res = new ReturnpBaseResponse();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "서버 에러 ");
			throw new ReturnpException(res);
		}
	}

	@Override
	public GreenPoint createRecommenderRPoint(int memberNo) {
		/* 추천인용 Green Point 생성*/
		GreenPoint greenPoint = new GreenPoint();
		greenPoint.setMemberNo(memberNo);
		greenPoint.setNodeNo(memberNo);
		greenPoint.setNodeType(AppConstants.NodeType.RECOMMENDER);
		greenPoint.setPointAmount((float)0);
		greenPoint.setNodeTypeName("recommender");
		this.greenPointMapper.insert(greenPoint);
		return greenPoint;
	}


}
