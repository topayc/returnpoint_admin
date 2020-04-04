package com.returnp.admin.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.dao.mapper.MainMapper;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.service.interfaces.SearchService;
import com.returnp.admin.service.interfaces.ShopService;

@Service
public class ShopServiceImpl implements ShopService{
	@Autowired MainMapper mainMapper;
	@Autowired SearchService searchService;
	

	@Override
	public ReturnpBaseResponse selectOrderReports(HashMap<String, Object> dbParams) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pcr = this.mainMapper.selectOrderReports(dbParams);
			res.setRows(pcr);
			res.setTotal(	pcr.size());
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
	public ReturnpBaseResponse selectPeriodOrderReports(HashMap<String, Object> dbParams) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pcr = this.mainMapper.selectPeriodOrderReports(dbParams);
			res.setRows(pcr);
			res.setTotal(	pcr.size());
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
	public ReturnpBaseResponse loadOrders(HashMap<String, Object> params) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pointCoupons = this.mainMapper.loadOrders(params);
			res.setRows(pointCoupons);
			res.setTotal(	this.searchService.selectTotalRecords());
			ResponseUtil.setSuccessResponse(res, "100" , "조회 성공");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "서버 에러 ");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse deleteOrder(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}


}
