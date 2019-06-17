package com.returnp.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.returnp.admin.dto.command.BranchCommand;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.PaymentRouter;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
public class PaymentRouterController extends ApplicationController {
	
	@Autowired SearchService searchService;;
	
	@ResponseBody
	@RequestMapping(value = "/paymentRouters", method = RequestMethod.GET)
	public ReturnpBaseResponse selectPaymentRouters(PaymentRouter router) {
		ArrayListResponse<PaymentRouter> slr3 = new ArrayListResponse<PaymentRouter>();
		slr3.setRows(this.searchService.selectPaymentRouters(router));
		slr3.setTotal(this.searchService.selectTotalRecords());
		this.setSuccessResponse(slr3);
		return slr3;
	} 

	@ResponseBody
	@RequestMapping(value = "/paymentRouters", method = RequestMethod.POST)
	public ReturnpBaseResponse createAffilaitePaymentRouter(PaymentRouter router) {
		ArrayListResponse<PaymentRouter> slr3 = new ArrayListResponse<PaymentRouter>();
		slr3.setRows(this.searchService.selectPaymentRouters(router));
		slr3.setTotal(this.searchService.selectTotalRecords());
		this.setSuccessResponse(slr3);
		return slr3;
	} 
}
