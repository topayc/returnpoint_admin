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
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.service.interfaces.MarketerService;

@Controller
@RequestMapping("/api")
@SessionAttributes("marketerFormInfo")
public class MarketerController extends ApplicationController {

	@Autowired MarketerService marketerService;;

	@RequestMapping(value = "/marketer/form/createForm", method = RequestMethod.GET)
	public String formMemberRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "marketerNo", defaultValue = "0") int marketerNo,
			Model model){

		model.addAttribute("marketerStatuses", CodeDefine.getMarketerStatusList());
		String view = null;
		if (action.equals("create")) {
			view = "template/form/marketer/createMarketer";
		}else if (action.equals("modify")){
			MarketerCommand command = new  MarketerCommand();
			command.setMarketerNo(marketerNo);
			ArrayListResponse<MarketerCommand> response  = (ArrayListResponse<MarketerCommand>)this.marketerService.findMarketerCommands(command);
			model.addAttribute("marketerFormInfo", response.getRows().get(0));
			view = "template/form/marketer/updateMarketer";
		}else if (action.equals("marketerDetailView")) {
			view = "template/form/marketer/marketerDetailView";
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value = "/marketer/gets", method = RequestMethod.GET)
	public ReturnpBaseResponse  getMarketers(
		@RequestParam(value = "marketerNo", required = false, defaultValue = "0") int  marketerNo) {
		MarketerCommand command = new MarketerCommand();
		if (marketerNo != 0) {
			command.setMarketerNo(marketerNo);
		}
		ReturnpBaseResponse res = this.marketerService.findMarketerCommands(command);
		return res;
		/*if (marketerNo != 0) {
			return res;
		}else {
			SingleDataObjectResponse<MarketerCommand> res2 = new SingleDataObjectResponse<MarketerCommand>();
			MarketerCommand c = ((ArrayListResponse<MarketerCommand>)res).getRows().get(0);
			res2.setData(c);
			return res2;
		}*/
	}
	
	@ResponseBody
	@RequestMapping(value = "/marketer/create", method = RequestMethod.POST)
	public  ReturnpBaseResponse createMarketer(@RequestParam(value = "count", required = true) int  count, HttpSession httpSession, Model model) {
		return this.marketerService.createMarketer(count);
	}
	
	@ResponseBody
	@RequestMapping(value = "/marketer/update", method = RequestMethod.POST)
	public  ReturnpBaseResponse updateMarketer( @ModelAttribute("marketerFormInfo") MarketerCommand  marketCommand,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model) {
		ReturnpBaseResponse  res = this.marketerService.udpateMarketer((MarketerCommand)marketCommand);
		if (res.getResultCode().equals("100")) {
			sessionStatus.setComplete();
		}
		return res;
	}
	
	
	/**
	 * @param marketerNo
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/marketer/delete", method = RequestMethod.POST)
	public  ReturnpBaseResponse deleteMarketer(int  marketerNo, Model model) {
		return null;
	}
}
