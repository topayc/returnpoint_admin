package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.command.PaymentTransactionCommand;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.PaymentTransaction;

@Transactional
public interface PaymentTransactionService {

	ReturnpBaseResponse createPaymentTransaction(PaymentTransaction transaction);

	ReturnpBaseResponse createNewPaymentTransaction(PaymentTransactionCommand transactionCommand);
	
	ReturnpBaseResponse checkPaymentTrasnsaction(PaymentTransaction transaction);
	
	int deleteByPrimaryKey(Integer paymentTransactionNo);

	int insert(PaymentTransaction record);

	int insertSelective(PaymentTransaction record);

	PaymentTransaction selectByPrimaryKey(Integer paymentTransactionNo);

	int updateByPrimaryKeySelective(PaymentTransaction record);

	int updateByPrimaryKey(PaymentTransaction record);
	
	ReturnpBaseResponse reaccumulatePaymentTransaction(int paymentTransactionNo, String reaccmulatetType);
}
