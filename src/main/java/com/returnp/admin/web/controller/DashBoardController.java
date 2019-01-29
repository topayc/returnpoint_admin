package com.returnp.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.model.DashBoard;
import com.returnp.admin.model.DashBoardChart;
import com.returnp.admin.service.interfaces.DashBoardService;

@Controller
@RequestMapping("/api")

public class DashBoardController extends ApplicationController {

	@Autowired DashBoardService dashBoardService;
	
	@ResponseBody
	@RequestMapping(value = "/dashBoard/get", method = RequestMethod.GET)
	public ReturnpBaseResponse getDashBoard(){
		ObjectResponse<DashBoard> res = new ObjectResponse<DashBoard>();
		res.setData(this.dashBoardService.select());
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/dashBoard/getChart", method = RequestMethod.GET)
	public ReturnpBaseResponse getDashBoardChart(){
		ArrayListResponse<DashBoardChart> res = new ArrayListResponse<DashBoardChart>();
		res.setRows(this.dashBoardService.selectForChart());
		this.setSuccessResponse(res);
		return res;
	}
}
