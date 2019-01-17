package com.returnp.admin.service.interfaces;

import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.model.PaymentTransaction;

public interface ExecutorService {
	public void executePaymenTransactionPointback(String command, int no);
	public BaseResponse accumulateRequest(PaymentTransaction transaction);
	public BaseResponse cancelAccumulateRequest(Integer paymentTransactionNo);
	public BaseResponse sendRequest(String urlData);
		
}
