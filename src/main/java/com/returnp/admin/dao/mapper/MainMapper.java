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

	/* 포인트 쿠폰 */
	public ArrayList<HashMap<String, Object>> selectPointCouponReports(HashMap<String, Object> dbParams);
	public ArrayList<HashMap<String, Object>> selectPeriodPointCouponReports(HashMap<String, Object> dbParams);
	public ArrayList<HashMap<String, Object>> loadPointCoupons(HashMap<String, Object> dbParams);

	/* 포인트 쿠폰 트랜잭션 */
	public ArrayList<HashMap<String, Object>> selectPointCouponTransactionReports(HashMap<String, Object> dbParams);
	public ArrayList<HashMap<String, Object>> selectPeriodPointCouponTransactionReportsReports(
			HashMap<String, Object> dbParams);
	public ArrayList<HashMap<String, Object>> loadPointCouponTransactions(HashMap<String, Object> dbParams);

	public ArrayList<HashMap<String, Object>> selectPointCodeIssueRequestReports(HashMap<String, Object> dbParams);

	public ArrayList<HashMap<String, Object>> selectPeriodPointCodeIssueRequestReports(HashMap<String, Object> dbParams);

	public ArrayList<HashMap<String, Object>> selectPointCodeIssueReports(HashMap<String, Object> dbParams);

	public ArrayList<HashMap<String, Object>> selectPeriodPointCodeIssueReports(HashMap<String, Object> dbParams);

	public ArrayList<HashMap<String, Object>> loadPointCodeIssueRequests(HashMap<String, Object> params);

	public ArrayList<HashMap<String, Object>> loadPointCodeIssues(HashMap<String, Object> params);

	public ArrayList<HashMap<String, Object>> selectPointCodeTransactionReports(HashMap<String, Object> dbParams);

	public ArrayList<HashMap<String, Object>> selectPeriodPointCodeTransactionReports(HashMap<String, Object> dbParams);

	public ArrayList<HashMap<String, Object>> loadPointCodeTransactions(HashMap<String, Object> params);

	public ArrayList<HashMap<String, Object>> selectPointCodePointbackRecords(HashMap<String, Object> param);
}