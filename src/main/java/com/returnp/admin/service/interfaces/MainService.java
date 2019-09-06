package com.returnp.admin.service.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;

@Transactional
public interface MainService {
	public ReturnpBaseResponse selectSalesReports(HashMap<String, Object> dbParams);

	public ReturnpBaseResponse reportPaymentTransactions(HashMap<String, Object> dbParams);

	public ReturnpBaseResponse selectPeriodSalesReports(HashMap<String, Object> dbParams);
	
	public ReturnpBaseResponse reportGpointPayments(HashMap<String, Object> dbParams);
	public ReturnpBaseResponse selectGpointPayments(HashMap<String, Object> dbParams);
	public ReturnpBaseResponse insertGpointPayment(HashMap<String, Object> dbParams);
	public ReturnpBaseResponse updateGpointPayment(HashMap<String, Object> dbParams);

	public ReturnpBaseResponse reportPeriodGpointPayments(HashMap<String, Object> dbParams);
}
	