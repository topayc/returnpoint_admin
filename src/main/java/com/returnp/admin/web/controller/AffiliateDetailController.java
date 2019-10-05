package com.returnp.admin.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.AffiliateDetail;
import com.returnp.admin.service.interfaces.AffiliateDetailService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("affiliateDetailFormInfo")
public class AffiliateDetailController extends ApplicationController{
	
	@Autowired SearchService searchService;
	@Autowired AffiliateDetailService affiliateDetailService;
	
	@ResponseBody
	@RequestMapping(value = "/affiliateDetails", method = RequestMethod.GET)
	public ReturnpBaseResponse  getAffiliateDetails( AffiliateDetail affiliateDetail) {
		return this.affiliateDetailService.selectAffiliateDetails(affiliateDetail);
	}
	
	@ResponseBody
	@RequestMapping(value = "/affiliateDetail/update", method = RequestMethod.POST)
	public  ReturnpBaseResponse updateAffiliateDetail( AffiliateDetail affiliateDetail, HttpServletRequest request) {
		return this.affiliateDetailService.updateAffiliateDetail(affiliateDetail,request);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/affiliateDetail/delete", method = RequestMethod.POST)
	public  ReturnpBaseResponse deleteAffiliateDetail(AffiliateDetail affiliateDetaill) {
		return  this.affiliateDetailService.deleteAffiliateDetail(affiliateDetaill);
	}
	
}
