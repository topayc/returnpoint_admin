package com.returnp.admin.service.interfaces;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.PaymentTransaction;

public interface ExecutorService {
	public void executePaymenTransactionPointback(String command, int no);
	public ReturnpBaseResponse accumulateRequest(PaymentTransaction transaction);
	public ReturnpBaseResponse cancelAccumulateRequest(Integer paymentTransactionNo);
	public ReturnpBaseResponse sendRequest(String urlData);
		
}
