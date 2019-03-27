package com.returnp.admin.web.controller;

import java.util.ArrayList;

import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.returnp.admin.dto.command.GiftCardOrderCommand;
import com.returnp.admin.dto.command.GiftCardOrderItemCommand;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.service.interfaces.GiftCardOrderService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("giftCardOrderFormInfo")
public class GiftCardOrderItemController extends ApplicationController{
	
	@Autowired SearchService searchService;
	@Autowired GiftCardOrderService giftCardOrderService;
	
	@ResponseBody
	@RequestMapping(value = "/giftCardOrderItems", method = RequestMethod.GET)
	public ReturnpBaseResponse selectProducts( SearchCondition searchCondition){
		GiftCardOrderItemCommand order= new GiftCardOrderItemCommand();
		if (StringUtils.isEmpty(searchCondition.getSearchKeyword())) {
			searchCondition.setSearchKeyword(null);
		}
		order.valueOf(searchCondition);
		ArrayListResponse<GiftCardOrderItemCommand> res = new ArrayListResponse<GiftCardOrderItemCommand>();
		ArrayList<GiftCardOrderItemCommand> orders = this.searchService.selectGiftCardOrderItemCommands(order);
		res.setRows(orders);
		res.setTotal(this.searchService.selectTotalRecords());
		this.setSuccessResponse(res);
		return res;
	}
}
