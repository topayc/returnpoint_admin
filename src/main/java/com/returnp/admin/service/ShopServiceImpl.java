package com.returnp.admin.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.common.ReturnpException;
import com.returnp.admin.dao.mapper.MainMapper;
import com.returnp.admin.dao.mapper.ShopProductOrderMapper;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.ShopProductOrder;
import com.returnp.admin.model.ShopProductOrderKey;
import com.returnp.admin.service.interfaces.SearchService;
import com.returnp.admin.service.interfaces.ShopService;

@Service
public class ShopServiceImpl implements ShopService{
	@Autowired MainMapper mainMapper;
	@Autowired SearchService searchService;
	@Autowired ShopProductOrderMapper shopProductOrderMapper;
	

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

	@Override
	public ReturnpBaseResponse changeOrderStatuses(ArrayList<Integer> shopProductOrderNos, String status) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			ShopProductOrder  order = null;
			ShopProductOrderKey	key = new ShopProductOrderKey();
			for(Integer shopProductOrderNo : shopProductOrderNos) {
				order = new ShopProductOrder();
				order.setShopProductOrderNo(shopProductOrderNo);
				order.setStatus(status);
				this.shopProductOrderMapper.updateByPrimaryKeySelective(order);
				
			}
			ResponseUtil.setSuccessResponse(res, "100" , "총 " +shopProductOrderNos.size()  + " 개의  주문 상태 변경을 완료했습니다");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "서버 에러 ");
			return res;
		}
	}



}
