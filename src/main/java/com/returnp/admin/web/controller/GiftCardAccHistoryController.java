package com.returnp.admin.web.controller;

import java.util.ArrayList;

import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.returnp.admin.dto.command.GiftCardAccHistoryCommand;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
public class GiftCardAccHistoryController extends ApplicationController{
	
	@Autowired SearchService searchService;
	
	
	@ResponseBody
	@RequestMapping(value = "/giftCardAccHistories", method = RequestMethod.GET)
	public ReturnpBaseResponse selectGiftCardAccHistories( SearchCondition searchCondition){
		GiftCardAccHistoryCommand historyCommand= new GiftCardAccHistoryCommand();
		if (StringUtils.isEmpty(searchCondition.getSearchKeyword())) {
			searchCondition.setSearchKeyword(null);
		}
		historyCommand.valueOf(searchCondition);
		ArrayListResponse<GiftCardAccHistoryCommand> res = new ArrayListResponse<GiftCardAccHistoryCommand>();
		ArrayList<GiftCardAccHistoryCommand> historyCommands = this.searchService.selectGiftCardAccHistoryCommands(historyCommand);
		res.setRows(historyCommands);
		res.setTotal(this.searchService.selectTotalRecords());
		this.setSuccessResponse(res);
		return res;
	}
}
