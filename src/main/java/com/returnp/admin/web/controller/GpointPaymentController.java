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

import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.service.interfaces.MainService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
public class GpointPaymentController extends ApplicationController{
	
	@Autowired MainService mainService;
	@Autowired SearchService searchService;
	
	@ResponseBody
	@RequestMapping(value = "/gpointPayment/report", method = RequestMethod.GET)
	public ReturnpBaseResponse  gpointPaymentReports(@RequestParam HashMap<String, Object> dbParams) {
		System.out.println("gpointPaymentReports");
		this.checkParameter(dbParams);
		return this.mainService.reportGpointPayments(dbParams);
	}

	@ResponseBody
	@RequestMapping(value = "/gpointPayment/reportPeriodGpointPayments", method = RequestMethod.GET)
	public ReturnpBaseResponse  reportPeriodGpointPayments(@RequestParam HashMap<String, Object> dbParams) {
		System.out.println("gpointPaymentReports");
		this.checkParameter(dbParams);
		return this.mainService.reportPeriodGpointPayments(dbParams);
	}
	
	@ResponseBody
	@RequestMapping(value = "/gpointPayments", method = RequestMethod.GET)
	public ReturnpBaseResponse  gpointPayments(@RequestParam HashMap<String, Object> dbParams) {
		System.out.println("gpointPayments");
		this.checkParameter(dbParams);
		return this.mainService.selectGpointPayments(dbParams);
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
			System.out.println(key + ":" +  params.get(key));
		}
		return params;
	}
}
