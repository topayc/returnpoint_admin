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
import com.returnp.admin.dto.command.BranchCommand;
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.dto.reponse.SingleDataObjectResponse;
import com.returnp.admin.model.Branch;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Member;
import com.returnp.admin.service.interfaces.BranchService;
import com.returnp.admin.service.interfaces.GreenPointService;
import com.returnp.admin.service.interfaces.MemberService;
import com.returnp.admin.service.interfaces.PointCoversionTransactionService;
import com.returnp.admin.service.interfaces.RedPointService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("branchFormInfo")
public class BranchController extends ApplicationController{
	
	@Autowired MemberService memberSerivice;
	@Autowired BranchService branchService;
	@Autowired SearchService searchService;;
	@Autowired MemberService memberService;
	@Autowired GreenPointService  greenPointService;
	@Autowired RedPointService redPointService;
	@Autowired PointCoversionTransactionService pointTransactionService;
	
	@RequestMapping(value = "/branch/form/createForm", method = RequestMethod.GET)
	public String formBranchRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "branchNo", defaultValue = "0") int memberNo,
			Model model){

		model.addAttribute("registTypes", CodeDefine.getRegistType());
		model.addAttribute("paymentStatuses", CodeDefine.getPaymentStatusList());
		model.addAttribute("paymentTypes", CodeDefine.getPaymentTypeList());
		model.addAttribute("nodeStatuses", CodeDefine.getNodeStatusList());
		model.addAttribute("authTypes", CodeDefine.getAuthtypeList());
	
		if (action.equals("create")) {
		}else if (action.equals("modify")){
			model.addAttribute("branchFormInfo", this.branchService.selectByPrimaryKey(memberNo));
		}
		return "template/form/node/createBranch";
	}
	
	@ResponseBody
	@RequestMapping(value = "/branch/get", method = RequestMethod.GET)
	public BaseResponse  getBranch(
			@RequestParam(value = "branchNo", required = true) int  branchNo) {
		
		Branch  branch= this.branchService.selectByPrimaryKey(branchNo);
		SingleDataObjectResponse<Branch> res = new SingleDataObjectResponse<Branch>();
		res.setData(branch);
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/branch/getBranchCommand", method = RequestMethod.GET)
	public BaseResponse  getBranchCommand(
			@RequestParam(value = "branchNo", required = true) int  branchNo) {
		
		BranchCommand  branchCommand= this.branchService.selectBranchCommandByPrimaryKey(branchNo);
		SingleDataObjectResponse<BranchCommand> res = new SingleDataObjectResponse<BranchCommand>();
		res.setData(branchCommand);
		this.setSuccessResponse(res);
		return res;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/branch/create", method = RequestMethod.POST)
	public  BaseResponse createBranch(
			Branch branch, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		Branch cond = new Branch();
		cond.setMemberNo(branch.getMemberNo());
		cond.setBranchEmail(branch.getBranchEmail());
		
		BaseResponse res = new BaseResponse();
		if (this.searchService.findBranches(cond).size() > 0) {
			this.setErrorResponse(res,"이미 지사로 등록되어 있는 회원입니다.");
		}else {
			if (branch.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
				AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
				branch.setRegAdminNo(adminSession.getAdmin().getAdminNo());
			}
			
			branch.setBranchCode(CodeGenerator.generatorBranchCode(null));
			branch.setGreenPointAccStatus("Y");
			branch.setRedPointAccStatus("Y");
			branch.setGreenPointUseStatus("Y");
			branch.setRedPointUseStatus("Y");
			this.branchService.insert(branch);    
			
			/* Member Table 업데이트*/
			Member m = new Member();
			m.setMemberNo(branch.getMemberNo());
			m.setIsBranch("Y");
			this.memberService.updateByPrimaryKeySelective(m);
			
			/* Green Point 생성, Red point는 회원 가입시 생성되므로 생성할 필요 없음*/
			GreenPoint greenPoint = new GreenPoint();
			greenPoint.setMemberNo(branch.getMemberNo());
			greenPoint.setNodeNo(branch.getBranchNo());
			greenPoint.setNodeType(AppConstants.NodeType.BRANCH);
			greenPoint.setPointAmount((float)0);
			greenPoint.setNodeTypeName("branch");
			this.greenPointService.insert(greenPoint);
			this.setSuccessResponse(res, "생성 완료");
		}
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/branch/update", method = RequestMethod.POST)
	public  BaseResponse updateBranch( 
			@ModelAttribute("branchFormInfo") Branch branch,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		if (branch.getRegType().equals(AppConstants.ReigistType.REGIST_BY_ADMIN)){
			AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
			branch.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		}
		
		BaseResponse res = new BaseResponse();
		this.branchService.updateByPrimaryKey(branch);
		this.setSuccessResponse(res, "수정 완료");
		sessionStatus.setComplete();
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/branch/delete", method = RequestMethod.POST)
	public  BaseResponse deleteBranch( 
			int  branchNo, Model model) {
		BaseResponse res = new BaseResponse();
		this.branchService.deleteByPrimaryKey(branchNo);
		this.setSuccessResponse(res, "삭제 완료");
		return res;
	}
}