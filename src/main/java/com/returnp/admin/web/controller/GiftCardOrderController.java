package com.returnp.admin.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.dto.GiftCardOrderForm;
import com.returnp.admin.dto.command.GiftCardOrderCommand;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.GiftCardOrder;
import com.returnp.admin.service.interfaces.GiftCardOrderService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("giftCardOrderFormInfo")
public class GiftCardOrderController extends ApplicationController{
	
	@Autowired SearchService searchService;
	@Autowired GiftCardOrderService giftCardOrderService;
	
	@ResponseBody
	@RequestMapping(value = "/giftCardOrders", method = RequestMethod.GET)
	public ReturnpBaseResponse selectProducts( SearchCondition searchCondition, HttpSession session){
		GiftCardOrderCommand order= new GiftCardOrderCommand();
		if (StringUtils.isEmpty(searchCondition.getSearchKeyword())) {
			searchCondition.setSearchKeyword(null);
		}
		order.valueOf(searchCondition);
		AdminSession adminSession = (AdminSession)session.getAttribute(AppConstants.ADMIN_SESSION);
		
		/*
		 * 슈퍼 관리자 및 본사가 아닐 경우, 해당 조직 코드의 주문 내역만 조회
		 * */
		if (!adminSession.getAuthType().equals(AppConstants.AdminType.TOP_HAPPY) && 
				!adminSession.getAuthType().equals(AppConstants.AdminType.HEAD_ORGAN) ) {
			order.setOrdererId(adminSession.getSaleOrgan().getOrganCode());
		}
		
		ArrayListResponse<GiftCardOrderCommand> res = new ArrayListResponse<GiftCardOrderCommand>();
		ArrayList<GiftCardOrderCommand> orders = this.searchService.selectGiftCardOrderCommands(order);
		res.setRows(orders);
		res.setTotal(this.searchService.selectTotalRecords());
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCardOrder/create", method = RequestMethod.POST)
	public ReturnpBaseResponse createGiftCardOrder(GiftCardOrderForm orderForm, HttpServletRequest request){
		System.out.println("###### createGiftCardOrder");
		return this.giftCardOrderService.createGiftCardOrder(orderForm);
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCardOrder/update", method = RequestMethod.POST)
	public ReturnpBaseResponse updateGiftCardOrder(GiftCardOrder order, HttpServletRequest request){
		System.out.println("###### updateGiftCardOrdeOrder");
		return this.giftCardOrderService.updateGiftCardOrder(order);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/giftCardOrder/delete", method = RequestMethod.POST)
	public ReturnpBaseResponse deleteGiftCardOrder( GiftCardOrder order){
		System.out.println("###### deleteGiftCardOrder");
		return this.giftCardOrderService.deleteGiftCardOrder(order);
	}
}
