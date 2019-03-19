package com.returnp.admin.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.returnp.admin.code.CodeDefine;
import com.returnp.admin.dto.command.GiftCardSalesOrganCommand;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.GiftCardSalesOrgan;
import com.returnp.admin.service.interfaces.GiftCardSalesOrganService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("giftCardsalesOrganFormInfo")
public class GiftCardSalesOrganController extends ApplicationController{
	
	@Autowired SearchService searchService;
	@Autowired GiftCardSalesOrganService organService;;
	
	@RequestMapping(value = "/giftCardsalesOrgan/form/createForm", method = RequestMethod.GET)
	public String formProductRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "giftCardSalesOrganNo", defaultValue = "0") int productNo,
			Model model){

		model.addAttribute("giftCardSaleOrganDetailCodes", CodeDefine.getGiftCardSaleOrganDetailCodes());
		model.addAttribute("giftCardSaleOrganStatusList", CodeDefine.getGiftCardSaleOrganStatusList());
		if (action.equals("create")) {
		
		}else if (action.equals("modify")){
		}
		return "template/form/createGiftCardSalesOrgan";
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCardSalesOrgans", method = RequestMethod.GET)
	public ReturnpBaseResponse selectGiftCardSalesOrgans(SearchCondition searchCondition){
		GiftCardSalesOrganCommand organ = new GiftCardSalesOrganCommand();
		if (StringUtils.isEmpty(searchCondition.getSearchKeyword())) {
			searchCondition.setSearchKeyword(null);
		}
		organ.valueOf(searchCondition);
		organ.setOrder("organType asc");
		ArrayListResponse<GiftCardSalesOrganCommand> res = new ArrayListResponse<GiftCardSalesOrganCommand>();
		ArrayList<GiftCardSalesOrganCommand> organs = this.searchService.selectGiftCardSalesOrganCommands(organ);
		res.setRows(organs);
		res.setTotal(this.searchService.selectTotalRecords());
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCardSalesOrgan/create", method = RequestMethod.POST)
	public ReturnpBaseResponse createGiftCardSalesOrgan( GiftCardSalesOrgan record, HttpServletRequest request){
		System.out.println("###### createGiftCardSalesOrgan");
		return this.organService.createGiftCardSalesOrgan(record);
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCardSalesOrgan/update", method = RequestMethod.POST)
	public ReturnpBaseResponse udpateGiftCardSalesOrgan( 
			GiftCardSalesOrgan record, SessionStatus sessionStatus, BindingResult result, HttpServletRequest request){
		System.out.println("###### udpateGiftCardSalesOrgan");
		ReturnpBaseResponse res = this.organService.updateGiftCardSalesOrgan(record);
		sessionStatus.setComplete();
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCardSalesOrgan/delete", method = RequestMethod.POST)
	public ReturnpBaseResponse deleteGiftCardSalesOrgan( GiftCardSalesOrgan record){
		System.out.println("###### deleteGiftCardSalesOrgan");
		return this.organService.deleteGiftCardSalesOrgan(record);
	}
}
