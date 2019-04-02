package com.returnp.admin.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.returnp.admin.code.CodeDefine;
import com.returnp.admin.dto.command.GiftCardPaymentCommand;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.GiftCardIssue;
import com.returnp.admin.model.GiftCardPayment;
import com.returnp.admin.service.interfaces.GiftCardPaymentService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("giftCardPaymentFormInfo")
public class GiftCardPaymentController extends ApplicationController{
	
	@Autowired GiftCardPaymentService giftCardPaymentService;
	@Autowired SearchService searchService;
	
	@RequestMapping(value = "/giftCardPayment/form/createForm", method = RequestMethod.GET)
	public String formProductRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "giftCardPaymentNo", defaultValue = "0") int giftCardPaymentNo,
			Model model){

		model.addAttribute("productStatusList", CodeDefine.getProductStatuses());
		if (action.equals("create")) {
		
		}else if (action.equals("modify")){
			GiftCardPaymentCommand giftCardPayment= new GiftCardPaymentCommand();
			giftCardPayment.setGiftCardIssueNo(giftCardPaymentNo);
			model.addAttribute("giftCardIssueFormInfo", this.searchService.selectGiftCardPaymentCommands(giftCardPayment).get(0));
		}
		return "template/form/createGiftCard";
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCardPayments", method = RequestMethod.GET)
	public ReturnpBaseResponse selectGiftCardPayments(
			SearchCondition searchCondition){
		GiftCardPaymentCommand giftCardPayment = new GiftCardPaymentCommand();
		if (StringUtils.isEmpty(searchCondition.getSearchKeyword())) {
			searchCondition.setSearchKeyword(null);
		}
		giftCardPayment.valueOf(searchCondition);
		ArrayListResponse<GiftCardPaymentCommand> res = new ArrayListResponse<GiftCardPaymentCommand>();
		ArrayList<GiftCardPaymentCommand> giftCardPayments = this.searchService.selectGiftCardPaymentCommands(giftCardPayment);
		res.setRows(giftCardPayments);
		res.setTotal(this.searchService.selectTotalRecords());
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCardPayment/create", method = RequestMethod.POST)
	public ReturnpBaseResponse createGiftCardIssue( GiftCardPayment giftCardPayment, HttpServletRequest request){
		//System.out.println("###### createGiftCard");
		return this.giftCardPaymentService.createGiftCardPayment(giftCardPayment);
	}

	
	@ResponseBody
	@RequestMapping(value = "/giftCardPayment/update", method = RequestMethod.POST)
	public ReturnpBaseResponse udpateProduct( 
			@ModelAttribute("giftCardPaymentFormInfo")  GiftCardPayment giftCardPayment, 
			SessionStatus sessionStatus, BindingResult result, HttpServletRequest request){
		//System.out.println("###### updateProduct");
		ReturnpBaseResponse res = this.giftCardPaymentService.updateGiftCardPayment(giftCardPayment);
		sessionStatus.setComplete();
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCardPayment/delete", method = RequestMethod.POST)
	public ReturnpBaseResponse deleteGiftCard( GiftCardPayment giftCardPayment){
		//System.out.println("###### deleteGiftCard");
		return this.giftCardPaymentService.deleteGiftCardPayment(giftCardPayment);
	}
}
