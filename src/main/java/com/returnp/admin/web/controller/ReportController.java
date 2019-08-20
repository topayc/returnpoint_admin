package com.returnp.admin.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.service.interfaces.ReportService;

@Controller
@RequestMapping("/api")
public class ReportController extends ApplicationController{
	
	@Autowired ReportService reportService;
	
	@ResponseBody
	@RequestMapping(value = "/report/saleseReports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectSalesReports(@RequestParam HashMap<String, Object> dbParams) {
		for (Map.Entry<String, Object> entry : dbParams.entrySet()) {
			String key   = entry.getKey();
			String  value =  (String)entry.getValue();
			if (value.equals("") || value.equals("0")) {
				dbParams.put(key, null);
			}
			System.out.println(key + " : " + dbParams.get(key));
		}
		return this.reportService.selectSalesReports(dbParams);
	}

	@ResponseBody
	@RequestMapping(value = "/report/selectPeriodSalesReports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPeriodSalesReports(@RequestParam HashMap<String, Object> dbParams) {
		for (Map.Entry<String, Object> entry : dbParams.entrySet()) {
			String key   = entry.getKey();
			String  value =  (String)entry.getValue();
			if (value.equals("") || value.equals("0")) {
				dbParams.put(key, null);
			}
			System.out.println(key + " : " + dbParams.get(key));
		}
		return this.reportService.selectPeriodSalesReports(dbParams);
	}

	@ResponseBody
	@RequestMapping(value = "/report/loadPaymentTransactions", method = RequestMethod.GET)
	public ReturnpBaseResponse  loadPaymentTransactions(@RequestParam HashMap<String, Object> dbParams) {
		return this.reportService.reportPaymentTransactions(dbParams);
	}
}
