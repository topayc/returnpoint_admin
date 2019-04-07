package com.returnp.admin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.common.ReturnpException;
import com.returnp.admin.dao.mapper.GiftCardPaymentMapper;
import com.returnp.admin.dto.command.GiftCardIssueCommand;
import com.returnp.admin.dto.command.GiftCardPaymentCommand;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCardPayment;
import com.returnp.admin.service.interfaces.GiftCardPaymentService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class GiftCardPaymentServiceImpl implements GiftCardPaymentService{

	@Autowired SearchService searchService;;
	@Autowired GiftCardPaymentMapper giftCardPaymentMapper;
	@Override
	public ReturnpBaseResponse createGiftCardPayment(GiftCardPayment record) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ReturnpBaseResponse deleteGiftCardPayment(GiftCardPayment record) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ReturnpBaseResponse updateGiftCardPayment(GiftCardPayment record) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ReturnpBaseResponse changeGiftCardStatus(int giftCardPaymentNo, String refundStatus) {
		ObjectResponse<GiftCardPaymentCommand> res = new ObjectResponse<GiftCardPaymentCommand>();
		try {
			GiftCardPaymentCommand command = new GiftCardPaymentCommand();
			command.setGiftCardPaymentNo(giftCardPaymentNo);
			ArrayList<GiftCardPaymentCommand> commandList = this.searchService.selectGiftCardPaymentCommands(command);
			if (commandList.size() != 1) {
				ResponseUtil.setResponse(res, "2894", "해당 상품권의 결제 내역이 존재하지 않습니다");
				throw new ReturnpException(res);
			}
			
			commandList.get(0).setRefundStatus(refundStatus);
			
			this.giftCardPaymentMapper.updateByPrimaryKey(commandList.get(0));
		/*	int affectedRow = this.giftCardIssueMapper.updateByPrimaryKey(command);
			
			if (affectedRow < 0) {
				ResponseUtil.setResponse(res, "500", "상품권 상태 변경 에러");
				throw new ReturnpException(res);
			}*/
			res.setData(commandList.get(0));
			ResponseUtil.setSuccessResponse(res, "100" , "상품권 결제 상태 변경 완료");
			return res;
		}catch(ReturnpException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return e.getBaseResponse();
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품권 결제 상태 변경 에러");
			return res;
		}
	}
}
