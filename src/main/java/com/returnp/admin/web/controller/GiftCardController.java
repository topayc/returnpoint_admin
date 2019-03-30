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
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.GiftCard;
import com.returnp.admin.service.interfaces.GiftCardSearvice;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("giftCardFormInfo")
public class GiftCardController extends ApplicationController{
	
	@Autowired GiftCardSearvice giftCardService;
	@Autowired SearchService searchService;
	
	@RequestMapping(value = "/giftCard/form/createForm", method = RequestMethod.GET)
	public String formProductRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "giftCardNo", defaultValue = "0") int giftCardNo,
			Model model){

		model.addAttribute("productStatusList", CodeDefine.getProductStatuses());
		if (action.equals("create")) {
		
		}else if (action.equals("modify")){
			GiftCard giftCard = new GiftCard();
			giftCard.setGiftCardNo(giftCardNo);
			model.addAttribute("giftCardFormInfo", this.searchService.selectGiftCards(giftCard).get(0));
		}
		return "template/form/createGiftCard";
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCards", method = RequestMethod.GET)
	public ReturnpBaseResponse selectGiftCards(
			SearchCondition searchCondition){
		GiftCard giftCard = new GiftCard();
		if (StringUtils.isEmpty(searchCondition.getSearchKeyword())) {
			searchCondition.setSearchKeyword(null);
		}
		giftCard.valueOf(searchCondition);
		ArrayListResponse<GiftCard> res = new ArrayListResponse<GiftCard>();
		ArrayList<GiftCard> products = this.searchService.selectGiftCards(giftCard);
		res.setRows(products);
		res.setTotal(this.searchService.selectTotalRecords());
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCard/create", method = RequestMethod.POST)
	public ReturnpBaseResponse createGiftCard( GiftCard giftCard, HttpServletRequest request){
		//System.out.println("###### createGiftCard");
		return this.giftCardService.createGiftCard(giftCard, request.getSession().getServletContext().getRealPath("/assets/images/products"), "/assets/images/products");
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCard/update", method = RequestMethod.POST)
	public ReturnpBaseResponse udpateProduct( @ModelAttribute("giftCardFormInfo")  GiftCard giftCard, SessionStatus sessionStatus, BindingResult result, HttpServletRequest request){
		//System.out.println("###### updateProduct");
		ReturnpBaseResponse res = this.giftCardService.updateGiftCard(giftCard, request.getSession().getServletContext().getRealPath("/assets/images/products"), "/assets/images/products");
		sessionStatus.setComplete();
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCard/delete", method = RequestMethod.POST)
	public ReturnpBaseResponse deleteGiftCard( GiftCard giftCard){
		//System.out.println("###### deleteGiftCard");
		return this.giftCardService.deleteGiftCard(giftCard);
	}
}
