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
import com.returnp.admin.dto.command.GiftCardIssueCommand;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.GiftCard;
import com.returnp.admin.model.GiftCardIssue;
import com.returnp.admin.service.interfaces.GiftCardIssueService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("giftCardIssueFormInfo")
public class GiftCardIssueController extends ApplicationController{
	
	@Autowired GiftCardIssueService giftCardIssueService;
	@Autowired SearchService searchService;
	
	@RequestMapping(value = "/giftCardIssue/form/createForm", method = RequestMethod.GET)
	public String formProductRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "giftCardIssueNo", defaultValue = "0") int giftCardIssueNo,
			Model model){

		model.addAttribute("productStatusList", CodeDefine.getProductStatuses());
		if (action.equals("create")) {
		
		}else if (action.equals("modify")){
			GiftCardIssueCommand giftCardIssue = new GiftCardIssueCommand();
			giftCardIssue.setGiftCardIssueNo(giftCardIssueNo);
			model.addAttribute("giftCardIssueFormInfo", this.searchService.selectGiftCardIssueCommands(giftCardIssue).get(0));
		}
		return "template/form/createGiftCard";
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCardIssues", method = RequestMethod.GET)
	public ReturnpBaseResponse selectGiftCards(
			SearchCondition searchCondition){
		GiftCardIssueCommand giftCardIssue = new GiftCardIssueCommand();
		if (StringUtils.isEmpty(searchCondition.getSearchKeyword())) {
			searchCondition.setSearchKeyword(null);
		}
		giftCardIssue.valueOf(searchCondition);
		giftCardIssue.setOrder("A.giftCardIssueNo desc");
		ArrayListResponse<GiftCardIssueCommand> res = new ArrayListResponse<GiftCardIssueCommand>();
		ArrayList<GiftCardIssueCommand> giftCardIssues = this.searchService.selectGiftCardIssueCommands(giftCardIssue);
		res.setRows(giftCardIssues);
		res.setTotal(this.searchService.selectTotalRecords());
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCardIssue/create", method = RequestMethod.POST)
	public ReturnpBaseResponse createGiftCardIssue( GiftCardIssue giftCardIssue, HttpServletRequest request){
		//System.out.println("###### createGiftCard");
		return this.giftCardIssueService.createGiftCardIssue(giftCardIssue);
	}

	@ResponseBody
	@RequestMapping(value = "/giftCardIssue/createBatch", method = RequestMethod.POST)
	public ReturnpBaseResponse createBatchGiftCard( 
			@RequestParam(value = "giftCardOrderNo", required = true,defaultValue = "0") int giftCardOrderNo,
			HttpServletRequest request){
		//System.out.println("###### createGiftCard");
		return this.giftCardIssueService.createBatchGiftCardIssue(giftCardOrderNo);
	}

	/**
	 * 해당 주문에 대하여 발행된 상품권 모두 취소 삭제 
	 * @param giftCardOrderNo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/giftCardIssue/invalidateBatch", method = RequestMethod.POST)
	public ReturnpBaseResponse invalidateBatchGiftCard( 
			@RequestParam(value = "giftCardOrderNo", required = true,defaultValue = "0") int giftCardOrderNo,
			HttpServletRequest request){
		//System.out.println("###### createGiftCard");
		return this.giftCardIssueService.invalidate(giftCardOrderNo);
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCardIssue/update", method = RequestMethod.POST)
	public ReturnpBaseResponse udpateProduct( 
			@ModelAttribute("giftCardIssueFormInfo")  GiftCardIssue giftCardIssue, 
			SessionStatus sessionStatus, BindingResult result, HttpServletRequest request){
		//System.out.println("###### updateProduct");
		ReturnpBaseResponse res = this.giftCardIssueService.updateGiftCardIssue(giftCardIssue);
		sessionStatus.setComplete();
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCardIssue/delete", method = RequestMethod.POST)
	public ReturnpBaseResponse deleteGiftCard( GiftCardIssue giftCardIssue){
		//System.out.println("###### deleteGiftCard");
		return this.giftCardIssueService.deleteGiftCardIssue(giftCardIssue);
	}
}
