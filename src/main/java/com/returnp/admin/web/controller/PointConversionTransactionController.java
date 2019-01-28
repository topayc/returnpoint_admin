package com.returnp.admin.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.returnp.admin.code.CodeDefine;
import com.returnp.admin.code.CodeGenerator;
import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.dto.command.PointConversionTransactionCommand;
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.SingleDataObjectResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.Policy;
import com.returnp.admin.model.RedPoint;
import com.returnp.admin.model.SoleDist;
import com.returnp.admin.service.interfaces.GreenPointService;
import com.returnp.admin.service.interfaces.MemberService;
import com.returnp.admin.service.interfaces.RedPointService;
import com.returnp.admin.service.interfaces.SearchService;
import com.returnp.admin.service.interfaces.SoleDistService;

@Controller
@RequestMapping("/api")
@SessionAttributes("soleDistFormInfo")
public class PointConversionTransactionController extends ApplicationController{
	
	@Autowired SoleDistService soleDistService;
	@Autowired SearchService searchService;
	@Autowired MemberService memberService;
	@Autowired GreenPointService  greenPointService;
	@Autowired RedPointService redPointService;
	
	@ResponseBody
	@RequestMapping(value = "/pointConversionTransactions", method = RequestMethod.GET)
	public BaseResponse  getPointConversionTransactions(
			SearchCondition searchCondition, Model model) {
		
		PointConversionTransactionCommand pctCond = new PointConversionTransactionCommand();
		pctCond.valueOf(searchCondition);
		
		if (!"0".equals(searchCondition.getSearchNodeType())) {
			pctCond.setNodeType(searchCondition.getSearchNodeType());
		}
		
		if (!"0".equals(searchCondition.getSearchConversionStatus())) {
			pctCond.setConversionStatus(searchCondition.getSearchConversionStatus());
		}
		
		if (searchCondition.getSearchKeyword()!= null &&  !searchCondition.getSearchKeyword().trim().equals("")) {
			pctCond.setMemberEmail(searchCondition.getSearchKeyword());
			pctCond.setMemberName(searchCondition.getSearchKeyword());
			pctCond.setMemberPhone(searchCondition.getSearchKeyword());
		}
		
		ArrayListResponse<PointConversionTransactionCommand> res  = new  ArrayListResponse<PointConversionTransactionCommand>();
		ArrayList<PointConversionTransactionCommand> commands = this.searchService.findPointConversionTransactionCommands(pctCond);
		res.setRows(commands);
		res.setTotal(this.searchService.selectTotalRecords());	
		this.setSuccessResponse(res);
		return res;
	}
	
}
