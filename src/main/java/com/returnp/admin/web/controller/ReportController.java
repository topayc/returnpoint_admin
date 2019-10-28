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
public class ReportController extends ApplicationController{
	
	@Autowired MainService mainService;
	@Autowired SearchService searchService;
	
	/**
	 * 협력 업체별 적립 금액 총액 
	 * @return
	 */
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
		return this.mainService.selectSalesReports(dbParams);
	}

	@ResponseBody
	@RequestMapping(value = "/report/selectPeriodSalesReports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPeriodSalesReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.mainService.selectPeriodSalesReports(dbParams);
	}

	@ResponseBody
	@RequestMapping(value = "/report/loadPaymentTransactions", method = RequestMethod.GET)
	public ReturnpBaseResponse  loadPaymentTransactions(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.mainService.reportPaymentTransactions(dbParams);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/report/selectPointWithdrawalReports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPointWithdrawalReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.mainService.selectPointWithdrawalReports(dbParams);
	}

	@ResponseBody
	@RequestMapping(value = "/report/loadPointWithdrawals", method = RequestMethod.GET)
	public ReturnpBaseResponse  loadPointWithdrawals(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.mainService.reportPointWithdrawals(dbParams);
	}
	
	@ResponseBody
	@RequestMapping(value = "/report/selectTotalPointWithdrawalReports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectTotalSalesReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		
		return this.mainService.selectTotalPointWithdrawalReports(dbParams);
	}

	
	@ResponseBody
	@RequestMapping(value = "/report/pointConversionReports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPointConversionReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.mainService.selectPointConversionReports(dbParams);
	}

	@ResponseBody
	@RequestMapping(value = "/report/periodPointConversionReports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPeriodPointConversionReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.mainService.selectPeriodPointConversionReports(dbParams);
	}

	@ResponseBody
	@RequestMapping(value = "/report/loadPointConversionTransactions", method = RequestMethod.GET)
	public ReturnpBaseResponse  loadPointConversions(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.mainService.loadPointConversions(dbParams);
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
