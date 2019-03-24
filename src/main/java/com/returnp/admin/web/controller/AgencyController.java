package com.returnp.admin.web.controller;

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
import com.returnp.admin.dto.command.AgencyCommand;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.model.Agency;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Member;
import com.returnp.admin.service.interfaces.AgencyService;
import com.returnp.admin.service.interfaces.BranchService;
import com.returnp.admin.service.interfaces.GreenPointService;
import com.returnp.admin.service.interfaces.MemberService;
import com.returnp.admin.service.interfaces.PointCoversionTransactionService;
import com.returnp.admin.service.interfaces.RedPointService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("agencyFormInfo")
public class AgencyController extends ApplicationController{
	
	@Autowired MemberService memberSerivice;
	@Autowired BranchService branchService;
	@Autowired AgencyService agencyService;
	@Autowired SearchService searchService;
	@Autowired MemberService memberService;
	@Autowired GreenPointService  greenPointService;
	@Autowired RedPointService redPointService;
	@Autowired PointCoversionTransactionService pointTransactionService;
	
	@RequestMapping(value = "/agency/form/createForm", method = RequestMethod.GET)
	public String formAgencyRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "agencyNo", defaultValue = "0") int agencyNo,
			Model model){

		model.addAttribute("registTypes", CodeDefine.getRegistTypes());
		model.addAttribute("paymentStatuses", CodeDefine.getPaymentStatuses());
		model.addAttribute("paymentTypes", CodeDefine.getPaymentTypest());
		model.addAttribute("nodeStatuses", CodeDefine.getNodeStatuses());
		model.addAttribute("authTypes", CodeDefine.getAuthTypes());
	
		if (action.equals("create")) {
		}else if (action.equals("modify")){
			model.addAttribute("agencyFormInfo", this.agencyService.selectByPrimaryKey(agencyNo));
		}
		return "template/form/node/createAgency";
	}
	
	@ResponseBody
	@RequestMapping(value = "/agency/get", method = RequestMethod.GET)
	public ReturnpBaseResponse  getAgency(
			@RequestParam(value = "agencyNo", required = true) int  agencyNo) {
		
		Agency agency= this.agencyService.selectByPrimaryKey(agencyNo);
		ObjectResponse<Agency> res = new ObjectResponse<Agency>();
		res.setData(agency);
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/agency/getAgencyCommand", method = RequestMethod.GET)
	public ReturnpBaseResponse  getAgencyCommand(
			@RequestParam(value = "agencyNo", required = true) int  agencyNo) {
		
		AgencyCommand agencyCommand= this.agencyService.selectAgencyCommandByPrimaryKey(agencyNo);
		ObjectResponse<AgencyCommand> res = new ObjectResponse<AgencyCommand>();
		res.setData(agencyCommand);
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/agency/create", method = RequestMethod.POST)
	public  ReturnpBaseResponse createAgency(
			Agency agency, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		Agency cond = new Agency();
		cond.setMemberNo(agency.getMemberNo());
		cond.setAgencyEmail(agency.getAgencyEmail());
		
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		if (this.searchService.findAgencies(cond).size() > 0) {
			this.setErrorResponse(res,"이미 대리점 등록되어 있는 회원입니다.");
		}else {
			if (agency.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
				AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
				agency.setRegAdminNo(adminSession.getAdmin().getAdminNo());
			}
			
			agency.setAgencyCode(CodeGenerator.generatorAgencyCode(null));
			agency.setGreenPointAccStatus("Y");
			agency.setRedPointAccStatus("Y");
			agency.setGreenPointUseStatus("Y");
			agency.setRedPointUseStatus("Y");
			this.agencyService.insert(agency);
			
			/* Member Table 업데이트*/
			Member m = new Member();
			m.setMemberNo(agency.getMemberNo());
			m.setIsAgency("Y");
			this.memberService.updateByPrimaryKeySelective(m);
			
			/* Green Point 생성, Red point 회원 가입시 생성되므로 생성할 필요 없음*/
			GreenPoint greenPoint = new GreenPoint();
			greenPoint.setMemberNo(agency.getMemberNo());
			greenPoint.setNodeNo(agency.getAgencyNo());
			greenPoint.setNodeType(AppConstants.NodeType.AGENCY);
			greenPoint.setPointAmount((float)0);
			greenPoint.setNodeTypeName("agency");
			this.greenPointService.insert(greenPoint);
			
			this.setSuccessResponse(res, "생성 완료");
		}
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/agency/update", method = RequestMethod.POST)
	public  ReturnpBaseResponse updateAgencyr( 
			@ModelAttribute("agencyFormInfo") Agency agency,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		if (agency.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
			AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
			agency.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		}
		
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		this.agencyService.updateByPrimaryKey(agency);
		this.setSuccessResponse(res, "수정 완료");
		sessionStatus.setComplete();
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/agency/delete", method = RequestMethod.POST)
	public  ReturnpBaseResponse deleteAgency( 
			int  agencyNo, Model model) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		this.agencyService.deleteByPrimaryKey(agencyNo);
		this.setSuccessResponse(res, "삭제 완료");
		return res;
	}
	
}
