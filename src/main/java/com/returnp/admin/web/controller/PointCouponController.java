package com.returnp.admin.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.returnp.admin.common.AppConstants;
import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.PointCoupon;
import com.returnp.admin.service.interfaces.MainService;
import com.returnp.admin.service.interfaces.PointCouponService;

@Controller
@RequestMapping("/api")
@SessionAttributes("giftCardFormInfo")
public class PointCouponController extends ApplicationController{
	
	@Autowired PointCouponService pointCouponService;
	
	@ResponseBody
	@RequestMapping(value = "/pointCoupon/create", method = RequestMethod.POST)
	public ReturnpBaseResponse createPointCoupon(PointCoupon pointCoupon, int qty, HttpSession httpSession){
		AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
		ReturnpBaseResponse res = null;
		if (adminSession == null) {
			res = new ReturnpBaseResponse();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "1098", "관리자 세션이 없습니다. 관리자 로그인을 해주세요");
			return res;
		}else {
			return this.pointCouponService.createPointCoupon(pointCoupon,qty, httpSession);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointCoupon/change", method = RequestMethod.GET)
	public ReturnpBaseResponse changePointCouponStatus(PointCoupon pointCoupon, HttpSession httpSession){
		AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
		ReturnpBaseResponse res = null;
		if (adminSession == null) {
			res = new ReturnpBaseResponse();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "1098", "관리자 세션이 없습니다. 관리자 로그인을 해주세요");
			return res;
		}else {
			return this.pointCouponService.chanagePointCoupon(pointCoupon);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointCoupon/pointCouponReports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectSalesReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.pointCouponService.selectPointCouponReports(dbParams);
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointCoupon/periodPointCouponReports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPeriodSalesReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.pointCouponService.selectPeriodPointCouponReports(dbParams);
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointCoupon/pointCoupons", method = RequestMethod.GET)
	public ReturnpBaseResponse  loadPointCoupons(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.pointCouponService.loadPointCoupons(dbParams);
	}
	
	
	//// 포인트 적립 트랜 잭션 /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@ResponseBody
	@RequestMapping(value = "/pointCoupon/pointCouponTransactionReports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPointCouponTransactionReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.pointCouponService.selectPointCouponTransactionReports(dbParams);
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointCoupon/periodpointCouponTransactionReports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPeriodPointCouponTransactonReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.pointCouponService.selectPeriodPointCouponTransactionReportsReports(dbParams);
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointCoupon/pointCouponTransactions", method = RequestMethod.GET)
	public ReturnpBaseResponse  loadPointCouponTransactions(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.pointCouponService.loadPointCouponTransactions(dbParams);
	}
	
	
	private HashMap<String, Object> checkParameter(HashMap<String, Object> params){
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key   = entry.getKey();
			String  value =  (String)entry.getValue();
			if (!key.equals("offset") && !key.equals("page") && !key.equals("pageSize") && !key.equals("pagination") && !key.equals("total")) {
				if (value.equals("") || value.equals("0")) {
					params.put(key, null);
				}
			}
/*	System.out.println(key + ":" +  params.get(key));*/
		}
		return params;
	}
	
}
