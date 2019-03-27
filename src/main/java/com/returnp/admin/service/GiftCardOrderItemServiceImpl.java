package com.returnp.admin.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.dao.mapper.GiftCardOrderItemMapper;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCardOrderItem;
import com.returnp.admin.service.interfaces.GiftCardOrderItemService;

@Service
public class GiftCardOrderItemServiceImpl implements GiftCardOrderItemService{

	@Autowired GiftCardOrderItemMapper giftCardOrderItemMapper;
	
	@Override
	public ReturnpBaseResponse createGiftCardOrderItem(GiftCardOrderItem orderItem) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		File file = null;
		try {
			int affectedRow = this.giftCardOrderItemMapper.insert(orderItem);
			if (affectedRow != 1) {
				throw new Exception();
			}
			ResponseUtil.setSuccessResponse(res, "100" , "상품권 주문 아이템 생성완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품권 주문 아이템 생성 에러");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse updateGiftCardOrderItem(GiftCardOrderItem orderItem) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			int affectedRow = this.giftCardOrderItemMapper.updateByPrimaryKeySelective(orderItem);
			if (affectedRow != 1) {
				throw new Exception();
			}
			ResponseUtil.setResponse(res, "100" , "상품권 주문 아이템수정 완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품권 주문 아이템수정 에러");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse deleteGiftCardOrderItem(GiftCardOrderItem orderItem) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			int affectedRow = this.giftCardOrderItemMapper.updateByPrimaryKey(orderItem);
			if (affectedRow != 1) {
				throw new Exception();
			}
			ResponseUtil.setSuccessResponse(res, "100" , "상품권 주문 아이템 삭제 완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품권 주문 아이템 삭제 에러");
			return res;
		}
	}


}
