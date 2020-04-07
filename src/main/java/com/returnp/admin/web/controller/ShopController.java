package com.returnp.admin.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.service.interfaces.PointCodeService;
import com.returnp.admin.service.interfaces.ShopService;

@Controller
@RequestMapping("/api")
public class ShopController extends ApplicationController{
	
	@Autowired ShopService  shopService;
	
	// --------------------------------------------------------------------------------------------------------------------
	// 포인트 코드발급 요청 컨트롤러 메서드 
	// --------------------------------------------------------------------------------------------------------------------

	@ResponseBody
	@RequestMapping(value = "/shop/orders/reports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPointCodeIssueRequestReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.shopService.selectOrderReports(dbParams);
	}
	
	@ResponseBody
	@RequestMapping(value = "/shop/orders/periodReports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPeriodPointCodeIssueRequestReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.shopService.selectPeriodOrderReports(dbParams);
	}
	
	@ResponseBody
	@RequestMapping(value = "/shop/orders/get", method = RequestMethod.GET)
	public ReturnpBaseResponse  loadPointCodeIssueRequests(@RequestParam HashMap<String, Object> params) {
		this.checkParameter(params);
		return this.shopService.loadOrders(params);
	}

	@ResponseBody
	@RequestMapping(value = "/shop/orders/delete", method = RequestMethod.POST)
	public ReturnpBaseResponse  deletePointCodeIssueRequest(@RequestParam HashMap<String, Object> params) {
		return this.shopService.deleteOrder(params);
	}

	@ResponseBody
	@RequestMapping(value = "/shop/orders/changeStatus", method = RequestMethod.POST)
	public ReturnpBaseResponse  changeStatus(
			@RequestParam(value = "shopProductOrderNos[]", required = true) ArrayList<Integer>  shopProductOrderNos, 
			@RequestParam(value = "status", required = true) String status) {
		return this.shopService.changeOrderStatuses(shopProductOrderNos, status);
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
		}
		return params;
	}
	
}
