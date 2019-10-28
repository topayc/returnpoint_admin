package com.returnp.admin.dao.mapper;

import java.util.ArrayList;
import java.util.HashMap;

public interface MainMapper {
	public int selectTotalRecords();

	public ArrayList<HashMap<String, Object>>selectSalesReports(HashMap<String, Object> dbParams);
	public ArrayList<HashMap<String, Object>>selectPeriodSalesReports(HashMap<String, Object> dbParams);
	public ArrayList<HashMap<String, Object>> reportPaymentTransactions(HashMap<String, Object> dbParams);
	

	public ArrayList<HashMap<String, Object>>selectPeriodGpointPaymentReports(HashMap<String, Object> dbParams);
	public ArrayList<HashMap<String, Object>>reportGpointPayments(HashMap<String, Object> dbParams);
	public ArrayList<HashMap<String, Object>>selectGpointPayments(HashMap<String, Object> dbParams);

	public int insertGpointPayment(HashMap<String, Object> dbParams);
	public int updateGpointPayment(HashMap<String, Object> dbParams);

	public ArrayList<HashMap<String, Object>> selectPointWithdrawalReports(HashMap<String, Object> dbParams);
	public ArrayList<HashMap<String, Object>> reportPointWithdrawals(HashMap<String, Object> dbParams);

	public ArrayList<HashMap<String, Object>> selectTotalPointWithdrawalReports(HashMap<String, Object> dbParams);

	public ArrayList<HashMap<String, Object>> selectPointConversionReports(HashMap<String, Object> dbParams);

	public ArrayList<HashMap<String, Object>> selectPeriodPointConversionReports(HashMap<String, Object> dbParams);

	public ArrayList<HashMap<String, Object>> loadPointConversions(HashMap<String, Object> dbParams);
}