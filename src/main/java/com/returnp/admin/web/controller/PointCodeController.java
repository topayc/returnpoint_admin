package com.returnp.admin.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.service.interfaces.PointCodeService;

@Controller
@RequestMapping("/api")
@SessionAttributes("giftCardFormInfo")
public class PointCodeController extends ApplicationController{
	
	@Autowired PointCodeService  pointCodeService;
	
	@ResponseBody
	@RequestMapping(value = "/pointCode/pointCodeReports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPointCodeReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.pointCodeService.selectPointCodeReports(dbParams);
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointCode/periodPointCodeReports", method = RequestMethod.GET)
	public ReturnpBaseResponse  selectPeriodSalesReports(@RequestParam HashMap<String, Object> dbParams) {
		this.checkParameter(dbParams);
		return this.pointCodeService.selectPeriodPointCodeReports(dbParams);
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
