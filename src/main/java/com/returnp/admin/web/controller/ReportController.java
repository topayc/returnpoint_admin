package com.returnp.admin.web.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
public class ReportController extends ApplicationController{
	
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
}
