package com.returnp.admin.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.dao.mapper.GiftCardOrderMapper;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCardOrder;
import com.returnp.admin.service.interfaces.GiftCardOrderService;

@Service
public class GiftCardOrderServiceImpl implements GiftCardOrderService{

	@Autowired GiftCardOrderMapper giftCardOrderMapper;
	
	@Override
	public ReturnpBaseResponse createGiftCardOrder(GiftCardOrder order) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		File file = null;
		try {
			int affectedRow = this.giftCardOrderMapper.insert(order);
			if (affectedRow != 1) {
				throw new Exception();
			}
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
	public ReturnpBaseResponse deleteGiftCardOrder(GiftCardOrder order) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			int affectedRow = this.giftCardOrderMapper.updateByPrimaryKey(order);
			if (affectedRow != 1) {
				throw new Exception();
			}
			ResponseUtil.setSuccessResponse(res, "100" , "상품권 주문 수정 완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품권 주문 수정 에러");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse updateGiftCardOrder(GiftCardOrder order) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			this.giftCardOrderMapper.deleteByPrimaryKey(order.getOrderNo());
			ResponseUtil.setResponse(res, "100" , "상품권 주문 삭제 완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품권 주문 삭제 에러");
			return res;
		}
	}

}
