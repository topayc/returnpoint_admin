package com.returnp.admin.service.interfaces;

import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.PaymentTransaction;

@Transactional
public interface PaymentTransactionService {

	ReturnpBaseResponse createPaymentTransaction(PaymentTransaction transaction);

	ReturnpBaseResponse createNewPaymentTransaction(PaymentTransaction transaction);
	
	ReturnpBaseResponse checkPaymentTrasnsaction(PaymentTransaction transaction);
	
	int deleteByPrimaryKey(Integer paymentTransactionNo);

	int insert(PaymentTransaction record);

	int insertSelective(PaymentTransaction record);

	PaymentTransaction selectByPrimaryKey(Integer paymentTransactionNo);

	int updateByPrimaryKeySelective(PaymentTransaction record);

	int updateByPrimaryKey(PaymentTransaction record);
	
	ReturnpBaseResponse reaccumulatePaymentTransaction(int paymentTransactionNo, String reaccmulatetType);
}
