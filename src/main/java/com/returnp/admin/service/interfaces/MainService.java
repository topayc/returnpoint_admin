package com.returnp.admin.service.interfaces;

import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;

@Transactional
public interface ReportService {
	public ReturnpBaseResponse selectSalesReports(HashMap<String, Object> dbParams);

	public ReturnpBaseResponse reportPaymentTransactions(HashMap<String, Object> dbParams);

	public ReturnpBaseResponse selectPeriodSalesReports(HashMap<String, Object> dbParams);
}
	