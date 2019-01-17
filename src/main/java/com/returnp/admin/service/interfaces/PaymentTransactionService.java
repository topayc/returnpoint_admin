package com.returnp.admin.service.interfaces;

import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.model.PaymentTransaction;

@Transactional
public interface PaymentTransactionService {

	BaseResponse createPaymentTransaction(PaymentTransaction transaction);

	BaseResponse createNewPaymentTransaction(PaymentTransaction transaction);
	
	BaseResponse checkPaymentTrasnsaction(PaymentTransaction transaction);
	
	int deleteByPrimaryKey(Integer paymentTransactionNo);

	int insert(PaymentTransaction record);

	int insertSelective(PaymentTransaction record);

	PaymentTransaction selectByPrimaryKey(Integer paymentTransactionNo);

	int updateByPrimaryKeySelective(PaymentTransaction record);

	int updateByPrimaryKey(PaymentTransaction record);
	
	BaseResponse reaccumulatePaymentTransaction(int paymentTransactionNo, String reaccmulatetType);
}
