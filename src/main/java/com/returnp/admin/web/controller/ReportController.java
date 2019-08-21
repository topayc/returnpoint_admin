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
import com.returnp.admin.service.interfaces.ReportService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
public class ReportController extends ApplicationController{
	
	@Autowired ReportService reportService;
	@Autowired SearchService searchService;
	
	@ResponseBody
	@RequestMapping(value = "/report/affilaiteSaleReport", method = RequestMethod.GET)
	public ReturnpBaseResponse  affilaiteSaleReport() {
		
		ArrayListResponse<HashMap<String, Object>> slr = new ArrayListResponse<HashMap<String, Object>>();
		ArrayList<HashMap<String, Object>> affialiteSaleReports = this.searchService.selectAffiliteSaleReport();
		this.setSuccessResponse(slr);
		slr.setRows(affialiteSaleReports);
		slr.setTotal(affialiteSaleReports.size());
		return slr;
	}
	
	@ResponseBody
	@RequestMapping(value = "/report/saleseReports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectSalesReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.reportService.selectSalesReports(dbParams);
	}

	@ResponseBody
	@RequestMapping(value = "/report/selectPeriodSalesReports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPeriodSalesReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.reportService.selectPeriodSalesReports(dbParams);
	}

	@ResponseBody
	@RequestMapping(value = "/report/loadPaymentTransactions", method = RequestMethod.GET)
	public ReturnpBaseResponse  loadPaymentTransactions(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.reportService.reportPaymentTransactions(dbParams);
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
