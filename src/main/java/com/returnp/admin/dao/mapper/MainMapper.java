package com.returnp.admin.dao.mapper;

import java.util.ArrayList;
import java.util.HashMap;

public interface ReportMapper {
	public ArrayList<HashMap<String, Object>>selectSalesReports(HashMap<String, Object> dbParams);
	public ArrayList<HashMap<String, Object>>selectPeriodSalesReports(HashMap<String, Object> dbParams);
	
	public int selectTotalRecords();

	public ArrayList<HashMap<String, Object>> reportPaymentTransactions(HashMap<String, Object> dbParams);
}