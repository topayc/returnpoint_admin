package com.returnp.admin.web.controller;

import javax.servlet.http.HttpSession;

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
import com.returnp.admin.dto.command.MarketerCommand;
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.model.Member;
import com.returnp.admin.service.interfaces.MarketerService;

@Controller
@RequestMapping("/api")
@SessionAttributes("marketerFormInfo")
public class MarketerController extends ApplicationController {

	@Autowired MarketerService marketerService;;

	@RequestMapping(value = "/marketer/form/createForm", method = RequestMethod.GET)
	public String formMemberRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "marketerNo", defaultValue = "0") int memberNo,
			Model model){

		model.addAttribute("marketerStatuses", CodeDefine.getMarketerStatusList());
		String view = null;
		if (action.equals("create")) {
			view = "template/form/marketer/createMarketer";
		}else if (action.equals("modify")){
			model.addAttribute("marketerFormInfo", null);
			view = "template/form/marketer/updateMarketer";
		}else if (action.equals("marketerDetailView")) {
			view = "template/form/marketer/marketerDetailView";
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "/marketer/gets", method = RequestMethod.GET)
	public BaseResponse  getMarketers(
		@RequestParam(value = "marketerNo", required = false) int  marketerNo) {
		return this.marketerService.findMarketerCommands(new MarketerCommand());
	}
	
	@ResponseBody
	@RequestMapping(value = "/marketer/create", method = RequestMethod.POST)
	public  BaseResponse createMarketer(@RequestParam(value = "count", required = true) int  count, HttpSession httpSession, Model model) {
		return this.marketerService.createMarketer(count);
	}
	
	@ResponseBody
	@RequestMapping(value = "/marketer/update", method = RequestMethod.POST)
	public  BaseResponse updateMarketer( @ModelAttribute("marketerFormInfo") Member  member,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model) {
		return null;
	}
	
	
	/**
	 * @param marketerNo
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/marketer/delete", method = RequestMethod.POST)
	public  BaseResponse deleteMarketer(int  marketerNo, Model model) {
		return null;
	}
}
