package com.returnp.admin.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.dao.mapper.ReportMapper;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.service.interfaces.ReportService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class ReportServiceImpl implements ReportService{

	@Autowired ReportMapper reportMapper;
	@Autowired SearchService searchService;
	@Override
	public ReturnpBaseResponse selectSalesReports(HashMap<String, Object> dbParams) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> sales = this.reportMapper.selectSalesReports(dbParams);
			res.setRows(sales);
			res.setTotal(sales.size());
			ResponseUtil.setSuccessResponse(res, "100" , "조회 성공");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "조회 에러 ");
			return res;
		}
	}
	@Override
	public ReturnpBaseResponse reportPaymentTransactions(HashMap<String, Object> dbParams) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> ptReports = this.reportMapper.reportPaymentTransactions(dbParams);
			res.setRows(ptReports);
			res.setTotal(	this.searchService.selectTotalRecords());
			ResponseUtil.setSuccessResponse(res, "100" , "조회 성공");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "조회 에러 ");
			return res;
		}
	}


}
