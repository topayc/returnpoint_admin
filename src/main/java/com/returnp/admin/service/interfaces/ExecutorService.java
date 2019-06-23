package com.returnp.admin.service.interfaces;

import com.returnp.admin.dto.command.PaymentTransactionCommand;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;

public interface ExecutorService {
	public void executePaymenTransactionPointback(String command, int no);
	public ReturnpBaseResponse accumulateRequest(PaymentTransactionCommand transactionCommand);
	public ReturnpBaseResponse cancelAccumulateRequest(Integer paymentTransactionNo);
	public ReturnpBaseResponse sendRequest(String urlData);
	public ReturnpBaseResponse cancelForcedAccumulateRequest(Integer paymentTransactionNo);
	public ReturnpBaseResponse acclForcedAccumulateRequest(Integer paymentTransactionNo);
		
}
