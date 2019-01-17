package com.returnp.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.dto.reponse.SearchListResponse;
import com.returnp.admin.dto.reponse.SingleDataObjectResponse;
import com.returnp.admin.model.DashBoard;
import com.returnp.admin.model.DashBoardChart;
import com.returnp.admin.service.interfaces.DashBoardService;

@Controller
@RequestMapping("/api")

public class DashBoardController extends ApplicationController {

	@Autowired DashBoardService dashBoardService;
	
	@ResponseBody
	@RequestMapping(value = "/dashBoard/get", method = RequestMethod.GET)
	public BaseResponse getDashBoard(){
		SingleDataObjectResponse<DashBoard> res = new SingleDataObjectResponse<DashBoard>();
		res.setData(this.dashBoardService.select());
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/dashBoard/getChart", method = RequestMethod.GET)
	public BaseResponse getDashBoardChart(){
		SearchListResponse<DashBoardChart> res = new SearchListResponse<DashBoardChart>();
		res.setRows(this.dashBoardService.selectForChart());
		this.setSuccessResponse(res);
		return res;
	}
}
