package com.returnp.admin.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.AppConstants;
import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.common.ReturnpException;
import com.returnp.admin.dao.mapper.GreenPointMapper;
import com.returnp.admin.dao.mapper.QueryMapper;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.AffiliateTid;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.MemberBankAccount;
import com.returnp.admin.model.PaymentTransaction;
import com.returnp.admin.service.interfaces.QueryService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class QueryServiceImpl implements QueryService {

	@Autowired QueryMapper queryMapper;
	@Autowired SearchService searchService;
	@Autowired GreenPointMapper greenPointMapper;;
	
	
	@Override
	public int deleteAffiliateTid(AffiliateTid affiliateTid) {
		return this.queryMapper.deleteAffiliateTid(affiliateTid);
	}
	@Override
	public int deleteGPoint(GreenPoint gPoint) {
		return this.queryMapper.deleteGPoint(gPoint);
	}
	@Override
	public int updateMemberBankAccount(MemberBankAccount account) {
		// TODO Auto-generated method stub
		return this.queryMapper.updateMemberBankAccount(account);
	}
	@Override
	public ReturnpBaseResponse salePontAcc(String salePontTarget, float salePointFeeRate, String targetDateStr) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		/*
		 * 해당 연월에 해당하는 포인트 지급 정보가 있는 지 확인
		 * 해당 지급 정보가 존재한다면, "이미 지급된 연월입니다"라는 메시지를 리턴함 
		 * 
		 * */
		try {
			if (salePontTarget == null || salePontTarget.trim().equals("") || salePontTarget.trim().length() == 0) {
				ResponseUtil.setResponse(res, "895", "포인트를 지급할 영업자가 없습니다. sales_point.properties 파일을 확인하세요.");
				return res;
			}
			
			if (salePointFeeRate == 0) {
				ResponseUtil.setResponse(res, "895", "영업자에게 지급할 포인트 수수료율이 지정되지 않았습니다. sales_point.properties 파일을 확인하세요.");
				return res;
			}
			
			String[] targetArr = salePontTarget.split(",");
			if (targetArr.length == 0) {
				ResponseUtil.setResponse(res, "895", "영업자 지정 형식이 옯바르지 않습니다. sales_point.properties 파일을 확인하세요.");
				return res;
			}
			
			
			ArrayList<PaymentTransaction> ptrList;
			ArrayList<Member> memberList;
			ArrayList<GreenPoint> greenPoints = null;
			
			HashMap<String , Object> dbParams = new HashMap<String, Object>();
			Member member;
			GreenPoint greenPoint;
			int approvalAmount  = 0; 
			int cancelAmount  = 0; 
			float updatePoint = 0.0f;
			
			String[] memberInfoArr;
			
			for( String target : targetArr) {
				memberInfoArr = target.split(":");
				
				if (memberInfoArr.length != 3) {
					ResponseUtil.setResponse(res, "885", "각 라인 영업자 지정 형식이 옯바르지 않습니다. sales_point.properties 파일을 확인하세요.");
					return res;
				}
				
				member = new Member();
				member.setMemberEmail(memberInfoArr[0]);
				memberList= this.searchService.findMembers(member);
				if (memberList.size() !=1 ) {
					ResponseUtil.setResponse(res, "756", memberInfoArr[0] + " 의 회원이 존재하지 않습니다. </br>sales_point.properties 파일을 확인해주세요");
					  throw new ReturnpException(res);
				}
				member = memberList.get(0);
				
				dbParams.put("affiliateNo", Integer.parseInt(memberInfoArr[1]));
				dbParams.put("searchMonth", targetDateStr);
				dbParams.put("paymentApprovalStatus", "1");
				
				/*결제 승인 총 금액 */
				approvalAmount = this.searchService.selectPaymentTransactionSumForSales(dbParams);
				if (approvalAmount == 0) {
					memberList = null;
					dbParams.clear();
					ptrList = null;
					greenPoints = null;
					approvalAmount  = 0; 
					cancelAmount  = 0; 
					updatePoint = 0.0f;
					continue;
				}
				
				/*결제 취소 총 금액 */
				dbParams.put("paymentApprovalStatus", "2");
				cancelAmount =  this.searchService.selectPaymentTransactionSumForSales(dbParams);
				
				/*결제 승인 과 취소금액을 감안하여, 최종 입금 G 포인트 */
				updatePoint =(salePointFeeRate / 100) * (approvalAmount - cancelAmount);
				
			    greenPoint =  new GreenPoint();
			    greenPoint.setMemberNo(member.getMemberNo());
			    greenPoint.setNodeType( AppConstants.NodeType.MEMBER);
		        
		        greenPoints = this.searchService.findGreenPoints(greenPoint);
		        if (greenPoints.size() != 1) {
		        	ResponseUtil.setResponse(res, "746", memberInfoArr[0] + " 해당 포인트 정보가 존재하지 않습니다. 관리자에게 문의 바랍니다");
					  throw new ReturnpException(res);
		        }
		        
		        greenPoint = greenPoints.get(0); 
		        greenPoint.setPointAmount(greenPoint.getPointAmount() +updatePoint );
		        this.greenPointMapper.updateByPrimaryKey(greenPoint);
		        
				memberList = null;
				dbParams.clear();
				ptrList = null;
				greenPoints = null;
				approvalAmount  = 0; 
				cancelAmount  = 0; 
				updatePoint = 0.0f;
			}
			
			ResponseUtil.setResponse(res, "100", "단말기 영업자 포인트가 지급되었습니다.");
			return res;
		   }catch(ReturnpException e) {
	            e.printStackTrace();
	            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	            res = e.getBaseResponse();
	            return res;
	        }catch(Exception e) {
	            e.printStackTrace();
	            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	        	ResponseUtil.setResponse(res, "500", "단말기 영업자 포인트 지급 작업 오류.");
	            return res;
	        }
	}
}
