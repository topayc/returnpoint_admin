package com.returnp.admin.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.osgl.xls.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.returnp.admin.code.CodeGenerator;
import com.returnp.admin.common.AppConstants;
import com.returnp.admin.common.DataMap;
import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.common.ReturnpException;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.PaymentTransaction;
import com.returnp.admin.service.interfaces.BasePointAccumulateService;
import com.returnp.admin.service.interfaces.UploadFileService;
import com.returnp.admin.utils.FileManager;
import com.returnp.admin.web.message.MessageUtils;

@Service
public class UploadFileServiceImpl implements UploadFileService {

	@Autowired MessageUtils messageUtils;
	@Autowired BasePointAccumulateService basePointAccumulateService;
	
	@Override
	public ReturnpBaseResponse uploadSalesFile(MultipartFile uploadFile, String saveDir ) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		ResponseUtil.setResponse(res, "100", this.messageUtils.getMessage("pointback.transaction_completed"));
		try {
			File file = FileManager.save(uploadFile, saveDir);
			ExcelReader reader = ExcelReader.builder()
				.map("회원이메일").to("memberEmail")
				.map("전화번호").to("memberPhone")
			    .map("가맹점").to("affiliateSerial")
			    .map("결제금액").to("paymentApprovalAmount")
			    .map("결제상태").to("paymentApprovalStatus")
			    .file(file)
			    .build();
			List<PaymentTransaction> data = reader.read(PaymentTransaction.class);
			PaymentTransaction pt = null;
			DataMap dataMap = null; 
			for (int i = 0;  i < data.size(); i++) {
				pt = data.get(i);
				
				if (StringUtils.isEmpty(pt.getMemberEmail())) {
					ResponseUtil.setResponse(res, "20000", (i + 1 ) + "  행에 회원 이메일이 누락되었습니다");
					throw new ReturnpException(res);
				}
				
				if (StringUtils.isEmpty(pt.getMemberPhone())) {
					ResponseUtil.setResponse(res, "20000", (i + 1 ) + "  행에 회원 전화번호가 누락되었습니다");
					throw new ReturnpException(res);
				}
				
				if (StringUtils.isEmpty(pt.getAffiliateSerial())) {
					ResponseUtil.setResponse(res, "20000", (i + 1 ) + "  행에 가맹점 아이디가 누락되었습니다");
					throw new ReturnpException(res);
				}
				
				if (StringUtils.isEmpty(pt.getPaymentApprovalAmount())) {
					ResponseUtil.setResponse(res, "20000", (i + 1 ) + "  행에 결제 금액이  누락되었습니다");
					throw new ReturnpException(res);
				}
				
				if (StringUtils.isEmpty(pt.getPaymentApprovalStatus())) {
					ResponseUtil.setResponse(res, "20000", (i + 1 ) + "  행에 결제 상태가 누락되었습니다");
					throw new ReturnpException(res);
				}
				
				dataMap = new DataMap();
				dataMap.put("pam", pt.getPaymentApprovalAmount());
				dataMap.put("pas", pt.getPaymentApprovalStatus());
				dataMap.put("pat", new Date());
				dataMap.put("pan", CodeGenerator.generatorPaymentApprovalNumber(null).trim());
				dataMap.put("af_id", pt.getAffiliateSerial().trim());
				dataMap.put("phoneNumber", pt.getMemberPhone().trim());
				dataMap.put("memberEmail", pt.getMemberEmail().trim());
				dataMap.put("acc_from", AppConstants.PaymentTransactionType.MANUAL);
				
				System.out.println("회원이메일 : " + pt.getMemberEmail());
				System.out.println("회원 전화 번호 : " + pt.getMemberPhone());
				System.out.println("가맹점 : " + pt.getAffiliateSerial());
				System.out.println("결제 금액 : " + pt.getPaymentApprovalAmount());
				System.out.println("결제 상태 : " + pt.getPaymentApprovalStatus());
				System.out.println("============================================================");

				if ( "0".equals(dataMap.getStr("pas").trim()))  {
					res = this.basePointAccumulateService.accumulate(dataMap);
					if (!res.resultCode.equals("100")) break;
						
				} 
				else if ("1".equals(dataMap.getStr("pas").trim())){
					/*res = this.basePointAccumulateService.cancelAccumulate(dataMap);
					if (!res.resultCode.equals("100")) break;*/
					ResponseUtil.setResponse(res, "906", this.messageUtils.getMessage("pointback.only_acc_can_by_file"));
					throw new ReturnpException(res);
				}
			}
			return res;
		}catch(ReturnpException e) {
			e.printStackTrace();
			if (!TransactionAspectSupport.currentTransactionStatus().isRollbackOnly()) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
			res = e.getBaseResponse();
			return res;
		}catch (Exception e) {
			e.printStackTrace();
			if (!TransactionAspectSupport.currentTransactionStatus().isRollbackOnly()) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
			
			ResponseUtil.setResponse(res, "20000", this.messageUtils.getMessage("sales_file_upload_fail"));
			return res;
		}
	}
}
