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
import com.returnp.admin.dto.GiftCardOrderForm;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCard;
import com.returnp.admin.model.GiftCardOrder;
import com.returnp.admin.model.GiftCardSalesOrgan;
import com.returnp.admin.service.interfaces.GiftCardOrderItemService;
import com.returnp.admin.service.interfaces.GiftCardOrderService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class GiftCardOrderServiceImpl implements GiftCardOrderService{

	@Autowired GiftCardOrderMapper giftCardOrderMapper;
	@Autowired SearchService searchService;;
	@Autowired GiftCardOrderItemService  orderItemService;;
	
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
			order.setOrderName(String.format("%s_%s",orderNumber , orderForm.getGiftCardSalesOrganName()));
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
			ResponseUtil.setResponse(res, "100" , "상품권 주문 수정 완료");
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


}
