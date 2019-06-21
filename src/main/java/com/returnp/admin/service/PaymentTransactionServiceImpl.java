package com.returnp.admin.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dao.mapper.PaymentTransactionMapper;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.Affiliate;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.PaymentTransaction;
import com.returnp.admin.service.interfaces.ExecutorService;
import com.returnp.admin.service.interfaces.PaymentTransactionService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class PaymentTransactionServiceImpl implements PaymentTransactionService {

	@Autowired PaymentTransactionMapper paymentTransactionMapper;
	@Autowired SearchService searchService;
	@Autowired ExecutorService paymentransactionExecutorService;
	
	@Override
	public ReturnpBaseResponse createPaymentTransaction(PaymentTransaction transaction) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		
		/*
		 * 존재하는 회원인지 조사 
		 * */
		Member memCond = new Member();
		memCond.setMemberNo(transaction.getMemberNo());
		ArrayList<Member> members = this.searchService.findMembers(memCond);
		if (members .size() != 1) {
			res.setMessage("잘못된 요청 - 존재하지 않는 회원");
			res.setResultCode("300");
			return res;
		} 
		
		/* 관리자에 의한 수동등록아닌 경우 가맹점 유효성 검사  */
		if (!transaction.getPaymentTransactionType().equals(AppConstants.PaymentTransactionType.MANUAL)) {
			/*
			 * 존재하는 가맹점인지 검사
			 * */
			Affiliate affiliateCond = new Affiliate();
			affiliateCond.setAffiliateSerial(transaction.getAffiliateSerial());
			ArrayList<Affiliate> affiliates = this.searchService.findAffiliates(affiliateCond);
			if (affiliates.size() != 1) {
				res.setMessage("잘못된 요청 -  잘못된 가맹점 시리얼 코드입니다.");
				res.setResultCode("300");
				return res;
			}	
			
			/*
			 * 해당 결제 내역이 이미 등록되어 있는지 검사
			 *  */
			PaymentTransaction ptCond = new PaymentTransaction();
			ptCond.setPaymentApprovalNumber(transaction.getPaymentApprovalNumber());
			ArrayList<PaymentTransaction> paymentTransactions = this.searchService.findPaymentTransactions(ptCond);
			if (paymentTransactions.size() != 0) {
				res.setMessage("잘못된 요청 - 이미 해당 결제 승인번호 처리 내역이 존재합니다.다시 확인해주세요");
				res.setResultCode("300");
				return res;
			}
		}
		
		transaction.setPointBackStatus(AppConstants.GreenPointAccStatus.POINTBACK_PROGRESS);
		this.insert(transaction);
		
		String cmd  = "PAYMENT_APPROVAL";
		if (AppConstants.PaymentApprovalStatus.PAYMENT_APPROVAL_CANCEL.equals(transaction.getPaymentApprovalStatus().trim())) {
			cmd ="PAYMENT CANCEL";
		}
		this.paymentransactionExecutorService.executePaymenTransactionPointback(cmd, transaction.getPaymentTransactionNo());
		
		res.setMessage("처리중- 잠시 후 확인해주세요");
		res.setResultCode("100");
		return res;
	}
	
	@Override
	public ReturnpBaseResponse createNewPaymentTransaction(PaymentTransaction transaction) {
		return this.paymentransactionExecutorService.accumulateRequest(transaction);
	}

	
	@Override
	public ReturnpBaseResponse reaccumulatePaymentTransaction(int paymentTransactionNo, String reaccmulatetType) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		switch(reaccmulatetType) {
		case AppConstants.reaccumulateType.ACC_AFTER_DEL: /*결제 내역을 삭제하고 재 적립 요청*/
			break;
		case AppConstants.reaccumulateType.ACC_NOT_DEL: /*결제 내역을 삭제하지 않고 재 적립 요청*/
			String cmd  = "PAYMENT_APPROVAL";
			this.paymentransactionExecutorService.executePaymenTransactionPointback(cmd, paymentTransactionNo);
		}
		res.setMessage("처리중- 잠시 후 확인해주세요");
		res.setResultCode("100");
		return res;
	}
	
	@Override
	public ReturnpBaseResponse checkPaymentTrasnsaction(PaymentTransaction transaction) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		return res;
	}

	
	@Override
	public int deleteByPrimaryKey(Integer paymentTransactionNo) {
		// TODO Auto-generated method stub
		return this.paymentTransactionMapper.deleteByPrimaryKey(paymentTransactionNo);
	}

	@Override
	public int insert(PaymentTransaction record) {
		// TODO Auto-generated method stub
		return this.paymentTransactionMapper.insert(record);
	}

	@Override
	public int insertSelective(PaymentTransaction record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PaymentTransaction selectByPrimaryKey(Integer paymentTransactionNo) {
		// TODO Auto-generated method stub
		return this.paymentTransactionMapper.selectByPrimaryKey(paymentTransactionNo);
	}

	@Override
	public int updateByPrimaryKeySelective(PaymentTransaction record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(PaymentTransaction record) {
		// TODO Auto-generated method stub
		return this.paymentTransactionMapper.updateByPrimaryKey(record);
	}


}
