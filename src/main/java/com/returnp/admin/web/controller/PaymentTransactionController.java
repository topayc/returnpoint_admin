package com.returnp.admin.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
import com.returnp.admin.dto.command.PaymentTransactionCommand;
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.SingleDataObjectResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.PaymentTransaction;
import com.returnp.admin.service.interfaces.ExecutorService;
import com.returnp.admin.service.interfaces.PaymentTransactionService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("paymentTransactionFormInfo")
public class PaymentTransactionController extends ApplicationController {
	
	@Autowired PaymentTransactionService paymentTransactionService;
	@Autowired SearchService searchService;
	@Autowired ExecutorService paymentransactionExecutorService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
	}

	@RequestMapping(value = "/paymentTransaction/form/createForm", method = RequestMethod.GET)
	public String formPaymentTransactionRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "paymentTransactionNo", defaultValue = "0") int paymentTransactionNo,
			Model model){

		model.addAttribute("registTypes", CodeDefine.getRegistType());
		model.addAttribute("paymentStatuses", CodeDefine.getPaymentStatusList());
		model.addAttribute("PaymentStatuses", CodeDefine.getVanPaymentStatusList());
		model.addAttribute("paymentTypes", CodeDefine.getPaymentTypeList());
		model.addAttribute("nodeStatuses", CodeDefine.getNodeStatusList());
		model.addAttribute("nodeTypeList", CodeDefine.getNodeTypeList());
		model.addAttribute("authTypes", CodeDefine.getAuthtypeList());
		model.addAttribute("paymentApprovalStatusList", CodeDefine.getPaymentApprovalStatusList());
		model.addAttribute("paymentTransactionTypeList", CodeDefine.getPaymentTransactionTypeList());
		model.addAttribute("action", action);
	
		if (action.equals("create")) {
		}else if (action.equals("modify")){
			model.addAttribute("paymentTransactionFormInfo", this.paymentTransactionService.selectByPrimaryKey(paymentTransactionNo));
		}
		return "template/form/payment/createPaymentTransactionForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/paymentTransaction/get", method = RequestMethod.GET)
	public BaseResponse  getPaymentTransaction(
			@RequestParam(value = "paymentTransactionNo", required = true) int  paymentTransactionNo) {
		
		PaymentTransaction  vanPaymentTransaction= this.paymentTransactionService.selectByPrimaryKey(paymentTransactionNo);
		SingleDataObjectResponse<PaymentTransaction> res = new SingleDataObjectResponse<PaymentTransaction>();
		res.setData(vanPaymentTransaction);
		this.setSuccessResponse(res);
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/paymentTransaction/genPan", method = RequestMethod.GET)
	public BaseResponse  genPan() {
		SingleDataObjectResponse<String> res = new SingleDataObjectResponse<String>();
		res.setData(CodeGenerator.generatorPaymentApprovalNumber(null));
		this.setSuccessResponse(res, "결제 번호 생성생성 ");
		return res;
	}

	
	@ResponseBody
	@RequestMapping(value = "/paymentTransactions", method = RequestMethod.GET)
	public  BaseResponse getPaymentTransactions(SearchCondition searchCondition, HttpSession httpSession, Model model) {
		
		PaymentTransaction vCond = new PaymentTransaction();
		vCond.setPaymentApprovalStatus(searchCondition.getSearchVanPaymentStatus());
		
		if (searchCondition.getSearchKeyword()!= null &&  !searchCondition.getSearchKeyword().trim().equals("")) {
			vCond.setAffiliateSerial(searchCondition.getSearchKeyword());
			vCond.setMemberEmail(searchCondition.getSearchKeyword());
			vCond.setPaymentApprovalNumber(searchCondition.getSearchKeyword());
			vCond.setMemberName(searchCondition.getSearchKeyword());
		}
		
		ArrayListResponse<PaymentTransaction> res = new ArrayListResponse<PaymentTransaction>();
		ArrayList<PaymentTransaction> list = this.searchService.findPaymentTransactions(vCond);
		res.setRows(list);
		res.setTotal(list.size());	
		this.setSuccessResponse(res);
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/paymentTransactionCommands", method = RequestMethod.GET)
	public  BaseResponse getPaymentTransactionCommands(SearchCondition searchCondition, HttpSession httpSession, Model model) {
		
		PaymentTransaction vCond = new PaymentTransaction();
		vCond.setPaymentApprovalStatus(searchCondition.getSearchVanPaymentStatus());
		
		if (searchCondition.getSearchKeyword()!= null &&  !searchCondition.getSearchKeyword().trim().equals("")) {
			vCond.setAffiliateSerial(searchCondition.getSearchKeyword());
			vCond.setMemberEmail(searchCondition.getSearchKeyword());
			vCond.setPaymentApprovalNumber(searchCondition.getSearchKeyword());
			vCond.setMemberName(searchCondition.getSearchKeyword());
		}
		
		ArrayListResponse<PaymentTransactionCommand> res = new ArrayListResponse<PaymentTransactionCommand>();
		ArrayList<PaymentTransactionCommand> list = this.searchService.findPaymentTransactionCommands(vCond);
		res.setRows(list);
		res.setTotal(list.size());	
		this.setSuccessResponse(res);
		return res;
	}
	
	/**
	 *  관리자 및 QR 코드 스캔에 의한 결제 승인 및 생성
	 * @param transaction
	 * @param httpSession
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/paymentTransaction/create", method = RequestMethod.GET)
	public  BaseResponse createPaymentTransaction(PaymentTransaction transaction, BindingResult result, HttpSession httpSession, Model model) {
		
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		if (AppConstants.PaymentTransactionType.ADMIN.equals(transaction.getPaymentTransactionType())) {
			AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
			transaction.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		}
		return this.paymentTransactionService.createPaymentTransaction(transaction);
	}
	
	@ResponseBody
	@RequestMapping(value = "/paymentTransaction/newCreate", method = RequestMethod.GET)
	public  BaseResponse createNewPaymentTransaction(
			PaymentTransaction transaction, BindingResult result, HttpSession httpSession, Model model) {
		System.out.println("paymentTransaction/newCreate");
		
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		if (AppConstants.PaymentTransactionType.ADMIN.equals(transaction.getPaymentTransactionType())) {
			AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
			transaction.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		}
		return this.paymentTransactionService.createNewPaymentTransaction(transaction);
	}
	
	
	/**
	 *  관리자 및 QR 코드 스캔에 의한 결제 승인 및 생성
	 * @param transaction
	 * @param httpSession
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/paymentTransaction/reaccumulate", method = RequestMethod.GET)
	public  BaseResponse reaccumulate(
			@RequestParam(value = "paymentTransactionNo", required = true,defaultValue = "1") int  paymentTransactionNo ,
			@RequestParam(value = "reaccmulatetType", required = true,defaultValue = "A") String  reaccmulatetType,
			HttpSession httpSession, Model model) {
		return this.paymentTransactionService.reaccumulatePaymentTransaction(paymentTransactionNo, reaccmulatetType);
	}
	
	
	
	/**
	 * 결제 취소 
	 * @param transaction
	 * @param httpSession
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/paymentTransaction/cancel", method = RequestMethod.POST)
	public  BaseResponse cancelPaymentTransaction(PaymentTransaction transaction, HttpSession httpSession, Model model) {
		System.out.println("#####  cancelPaymentTransaction 호출됨");
		return this.paymentransactionExecutorService.cancelAccumulateRequest(transaction.getPaymentTransactionNo());
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/paymentTransaction/update", method = RequestMethod.POST)
	public  BaseResponse updatePaymentTransaction( 
			@ModelAttribute("paymentTransactionFormInfo") PaymentTransaction  paymentTransaction,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model) {
/*		System.out.println("updateVanPaymentTransaction 호출됨");*/
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		if (paymentTransaction.getPaymentTransactionType().equals(AppConstants.VanPaymentStatus.ADMIN_MANUAL_REGIST)){
			AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
			paymentTransaction.setRegAdminNo(adminSession.getAdmin().getAdminNo());
		}
		
		BaseResponse res = new BaseResponse();
		this.paymentTransactionService.updateByPrimaryKey(paymentTransaction);
		this.setSuccessResponse(res, "수정 완료");
		sessionStatus.setComplete();
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/paymentTransaction/delete", method = RequestMethod.POST)
	public  BaseResponse deletePaymentTransaction( 
			int  paymentTransactionNo, Model model) {
		System.out.println("vanPaymentTransaction/delete");
		System.out.println(paymentTransactionNo);
		BaseResponse res = new BaseResponse();
		this.paymentTransactionService.deleteByPrimaryKey(paymentTransactionNo);
		this.setSuccessResponse(res, "삭제 완료");
		return res;
	}
	
}