package com.returnp.admin.web.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis.utils.StringUtils;
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
import org.springframework.web.servlet.View;

import com.returnp.admin.code.CodeDefine;
import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.dto.command.GiftCardIssueCommand;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.GiftCardIssue;
import com.returnp.admin.service.interfaces.GiftCardIssueService;
import com.returnp.admin.service.interfaces.SearchService;
import com.returnp.admin.view.ListExcelDownload;

@Controller
@RequestMapping("/api")
@SessionAttributes("giftCardIssueFormInfo")
public class GiftCardIssueController extends ApplicationController{
	
	@Autowired GiftCardIssueService giftCardIssueService;
	@Autowired SearchService searchService;
	
	@RequestMapping(value = "/giftCardIssue/form/createForm", method = RequestMethod.GET)
	public String formProductRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "giftCardIssueNo", defaultValue = "0") int giftCardIssueNo,
			Model model){

		model.addAttribute("productStatusList", CodeDefine.getProductStatuses());
		if (action.equals("create")) {
		
		}else if (action.equals("modify")){
			GiftCardIssueCommand giftCardIssue = new GiftCardIssueCommand();
			giftCardIssue.setGiftCardIssueNo(giftCardIssueNo);
			model.addAttribute("giftCardIssueFormInfo", this.searchService.selectGiftCardIssueCommands(giftCardIssue).get(0));
		}
		return "template/form/createGiftCard";
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCardIssues", method = RequestMethod.GET)
	public ReturnpBaseResponse selectGiftCards(
			SearchCondition searchCondition, HttpSession session){
		GiftCardIssueCommand giftCardIssue = new GiftCardIssueCommand();
		if (StringUtils.isEmpty(searchCondition.getSearchKeyword())) {
			searchCondition.setSearchKeyword(null);
		}
		giftCardIssue.valueOf(searchCondition);
		giftCardIssue.setOrder("A.giftCardIssueNo desc");
		
		/*
		 * 슈퍼 관리자 및 본사가 아닐 경우, 해당 조직 코드의 주문 내역만 조회
		 * */
		AdminSession adminSession = (AdminSession)session.getAttribute(AppConstants.ADMIN_SESSION);
		if (!adminSession.getAdminType().equals(AppConstants.AdminType.SUPER) && 
				!adminSession.getAdminType().equals(AppConstants.AdminType.HEAD_ORGAN) ) {
			giftCardIssue.setOrdererId(adminSession.getSaleOrgan().getOrganCode());
		}
		
		ArrayListResponse<GiftCardIssueCommand> res = new ArrayListResponse<GiftCardIssueCommand>();
		ArrayList<GiftCardIssueCommand> giftCardIssues = this.searchService.selectGiftCardIssueCommands(giftCardIssue);
		res.setRows(giftCardIssues);
		res.setTotal(this.searchService.selectTotalRecords());
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCardIssue/create", method = RequestMethod.POST)
	public ReturnpBaseResponse createGiftCardIssue( GiftCardIssue giftCardIssue, HttpServletRequest request){
		//System.out.println("###### createGiftCard");
		return this.giftCardIssueService.createGiftCardIssue(giftCardIssue);
	}

	@ResponseBody
	@RequestMapping(value = "/giftCardIssue/createBatch", method = RequestMethod.POST)
	public ReturnpBaseResponse createBatchGiftCard( 
			@RequestParam(value = "giftCardOrderNo", required = true,defaultValue = "0") int giftCardOrderNo,
			HttpServletRequest request){
		//System.out.println("###### createGiftCard");
		return this.giftCardIssueService.createBatchGiftCardIssue(giftCardOrderNo);
	}

	@ResponseBody
	@RequestMapping(value = "/giftCardIssue/change", method = RequestMethod.POST)
	public ReturnpBaseResponse changeGiftCardStatus( 
			@RequestParam(value = "giftCardIssueNo", required = true,defaultValue = "0") int giftCardIssueNo,
			@RequestParam(value = "giftCardStatus", required = true,defaultValue = "0") String giftCardStatus,
			
			HttpServletRequest request){
		
		return this.giftCardIssueService.changeGiftCardStatus(giftCardIssueNo, giftCardStatus);
	}
		
	@RequestMapping(value = "/giftCardIssue/issueExcelDownload", method = RequestMethod.GET)
	public View downloadExcel(@RequestParam(value = "giftCardOrderNo", required = true,defaultValue = "0") int giftCardOrderNo, 
			HttpServletRequest request, HttpServletResponse response, Model model) {
		/*System.out.println("###### issueExcelDownload");*/
		GiftCardIssueCommand giftCardIssue = new GiftCardIssueCommand();
		giftCardIssue.setGiftCardOrderNo(giftCardOrderNo);
		ArrayList<GiftCardIssueCommand> giftCardIssues = this.searchService.selectGiftCardIssueCommands(giftCardIssue);		
		model.addAttribute("giftCardIssuseList", giftCardIssues);
		return new ListExcelDownload();
	}

	/**
	 * 해당 주문에 대하여 발행된 상품권 모두 취소 삭제 
	 * @param giftCardOrderNo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/giftCardIssue/invalidateBatch", method = RequestMethod.POST)
	public ReturnpBaseResponse invalidateBatchGiftCard( 
			@RequestParam(value = "giftCardOrderNo", required = true,defaultValue = "0") int giftCardOrderNo,
			HttpServletRequest request){
		//System.out.println("###### createGiftCard");
		return this.giftCardIssueService.invalidate(giftCardOrderNo);
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCardIssue/update", method = RequestMethod.POST)
	public ReturnpBaseResponse udpateProduct( 
			@ModelAttribute("giftCardIssueFormInfo")  GiftCardIssue giftCardIssue, 
			SessionStatus sessionStatus, BindingResult result, HttpServletRequest request){
		//System.out.println("###### updateProduct");
		ReturnpBaseResponse res = this.giftCardIssueService.updateGiftCardIssue(giftCardIssue);
		sessionStatus.setComplete();
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/giftCardIssue/delete", method = RequestMethod.POST)
	public ReturnpBaseResponse deleteGiftCard( GiftCardIssue giftCardIssue){
		//System.out.println("###### deleteGiftCard");
		return this.giftCardIssueService.deleteGiftCardIssue(giftCardIssue);
	}
	
	/**
	 *  * 큐알코드만 생성하고 해당 큐알 코드 이미지 정보를 디비에 기록한 후, 생성된 큐알 이미지 url 정보 반환
	 * @param giftCardIssueNo
	 * @param type  A : 적립 큐알, P : 결제 큐알
	 * @returnjavascript:void(0)
	 */
	@ResponseBody
	@RequestMapping(value = "/giftCardIssue/createQr", method = RequestMethod.GET)
	public ReturnpBaseResponse createQrImage( int giftCardIssueNo,String  type, HttpServletRequest request ){
		/*System.out.println("###### createQrImage");*/
		return this.giftCardIssueService.createQrImage(
			giftCardIssueNo, type,request.getSession().getServletContext().getRealPath("/gift_qr/" + giftCardIssueNo),  "/gift_qr/" + giftCardIssueNo);
	}
	
	/**
	 * 큐알코드만 생성하고 해당 큐알 코드를 전송
	 * @param giftCardIssueNo
	 * @param type  A : 적립 큐알, P : 결제 큐알
	 * @throws IOException 
	 */
	@RequestMapping(value = "/giftCardIssue/downQrCode", method = RequestMethod.GET)
	public void downQrCord( int giftCardIssueNo,String  type, HttpServletRequest request, HttpServletResponse response ) throws IOException{
		/*System.out.println("###### createQrImage");*/
		ObjectResponse<byte[]> res  = (ObjectResponse<byte[]>) this.giftCardIssueService.downQrCoder(giftCardIssueNo, type);
		response.setContentType("image/png");
		response.getOutputStream().write(res.getData());
	}

	/**
	 * 지정된 상품권의 큐알 코드 일괄 생성
	 */
	@ResponseBody
	@RequestMapping(value = "/giftCardIssue/createQrBatch", method = RequestMethod.GET)
	public ReturnpBaseResponse createQrImageBatch( 
			@RequestParam(value = "giftCardIssueNos[]", required = true) ArrayList<Integer> giftCardIssueNos, 
			HttpServletRequest request, HttpServletResponse response ){
		System.out.println("###### createQrImageBatch");
		for (int i = 0; i < giftCardIssueNos.size(); i++) {
			System.out.println(giftCardIssueNos.get(i) + " : " + giftCardIssueNos.get(i));
		}
		return this.giftCardIssueService.createQrImageBatch( giftCardIssueNos, request, response);
	}

	/**
	 * 상품권 문자 전송	
	 */
	@ResponseBody
	@RequestMapping(value = "/giftCardIssue/sendGiftCardByMobile", method = RequestMethod.POST)
	public ReturnpBaseResponse sendGiftCardByMobile(
			@RequestParam(value = "pinNumbers[]", required = true) ArrayList<String> pinNumbers, 
			@RequestParam(value = "giftCardIssueNos[]", required = true) ArrayList<String> giftCardIssueNos, 
			@RequestParam(value = "receiverPhone", required = true) String receiverPhone, 
			HttpServletRequest request ){
		for (int i = 0; i < pinNumbers.size(); i++) {
			System.out.println(giftCardIssueNos.get(i) + " : " + pinNumbers.get(i));
		}
		return this.giftCardIssueService.sendGiftCardByMobile(pinNumbers, receiverPhone);
	}
}
