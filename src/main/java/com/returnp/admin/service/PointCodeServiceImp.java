package com.returnp.admin.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.code.CodeGenerator;
import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.common.ReturnpException;
import com.returnp.admin.dao.mapper.MainMapper;
import com.returnp.admin.dao.mapper.MemberConfigMapper;
import com.returnp.admin.dao.mapper.PointCodeIssueMapper;
import com.returnp.admin.dao.mapper.PointCodeIssueRequestMapper;
import com.returnp.admin.dao.mapper.SearchMapper;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.DeviceInfo;
import com.returnp.admin.model.GiftCardIssue;
import com.returnp.admin.model.MemberConfig;
import com.returnp.admin.model.PointCodeIssue;
import com.returnp.admin.model.PointCodeIssueRequest;
import com.returnp.admin.service.interfaces.AndroidPushService;
import com.returnp.admin.service.interfaces.IOSPushService;
import com.returnp.admin.service.interfaces.PointCodeService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class PointCodeServiceImp implements PointCodeService{
	@Autowired MainMapper mainMapper;
	@Autowired PointCodeIssueRequestMapper pointCodeIssueRequestMapper  ;
	@Autowired PointCodeIssueMapper pointCodeIssueMapper  ;
	@Autowired SearchService searchService;;
	@Autowired SearchMapper searchMapper;
	@Autowired MemberConfigMapper  memberConfigMapper;
	@Autowired AndroidPushService androidPushService;
	@Autowired IOSPushService iosPushService;
	
	// --------------------------------------------------------------------------------------------------------------------
	// 포인트 코드 발급 요청 서비스 메서드 
	// --------------------------------------------------------------------------------------------------------------------

	@Override
	public ReturnpBaseResponse selectPointCodeIssueRequestReports(HashMap<String, Object> dbParams) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pcr = this.mainMapper.selectPointCodeIssueRequestReports(dbParams);
			res.setRows(pcr);
			res.setTotal(	pcr.size());
			ResponseUtil.setSuccessResponse(res, "100" , "조회 성공");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "조회 에러 ");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse selectPeriodPointCodeIssueRequestReports(HashMap<String, Object> dbParams) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pcr = this.mainMapper.selectPeriodPointCodeIssueRequestReports(dbParams);
			res.setRows(pcr);
			res.setTotal(	pcr.size());
			ResponseUtil.setSuccessResponse(res, "100" , "조회 성공");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "조회 에러 ");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse chanagePointCodeRequestStatus(PointCodeIssueRequest pointCodeIssueRequest) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			PointCodeIssueRequest pr = this.pointCodeIssueRequestMapper.selectByPrimaryKey(pointCodeIssueRequest.getPointCodeIssueRequestNo());
			if (pr == null ){
				ResponseUtil.setResponse(res, "1008", "잘못된 요청 - 해당 포인트 코드 발행 요청 항목이 없습니다.");
				throw new ReturnpException(res);
			}
		
			this.pointCodeIssueRequestMapper.updateByPrimaryKeySelective(pointCodeIssueRequest);
			ResponseUtil.setSuccessResponse(res, "100" , "포인트 코드 발급 요청건  상태 변경 완료");
			return res;
		}catch(ReturnpException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return e.getBaseResponse();
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "포인트 코드 발급 요청건  상태 변경 변경 실패");
			return res;
		}
	}
	
	@Override
	public ReturnpBaseResponse loadPointCodeIssueRequests(HashMap<String, Object> params) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pointCoupons = this.mainMapper.loadPointCodeIssueRequests(params);
			res.setRows(pointCoupons);
			res.setTotal(	this.searchService.selectTotalRecords());
			ResponseUtil.setSuccessResponse(res, "100" , "조회 성공");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "서버 에러 ");
			return res;
		}
	}
	

	@Override
	public ReturnpBaseResponse issuePointCode(PointCodeIssue pointCodeIssue) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		String message = null;
		try {
			/*관련 포인트 발급 요청 테이블 업데이트*/
			PointCodeIssueRequest req = this.pointCodeIssueRequestMapper.selectByPrimaryKey(pointCodeIssue.getPointCodeIssueRequestNo());
			if (req == null) {
				ResponseUtil.setResponse(res, "2089", "잘못된 요청 - 포인트 코드 요청 정보가 존재하지 않아 포인트 적립 코드를 발행할 수 없습니다.");
				throw new ReturnpException(res);
			}
			
			/*발급상태로 변경*/
			req.setStatus("4");
			this.pointCodeIssueRequestMapper.updateByPrimaryKey(req);
			
			/*포인트 코드 발급 및 insert*/
			pointCodeIssue.setPointCode(CodeGenerator.genPointCode());
			pointCodeIssue.setUseStatus("1");
			pointCodeIssue.setPayAmount(req.getPayAmount());
			pointCodeIssue.setAccPointAmount(req.getAccPointAmount());
			pointCodeIssue.setAccPointRate(req.getAccPointRate());
			pointCodeIssue.setMemberNo(req.getMemberNo());
			this.pointCodeIssueMapper.insert(pointCodeIssue);
			
			message = "포인트 적립 코드 발급 완료 ";
			
			/* 포인트코드 발급 확인 푸시 메시지 전송*/
			/*
			 * 디바이스 정보 확인
			 * */
			DeviceInfo deviceInfo = new DeviceInfo();
			deviceInfo.setMemberNo(req.getMemberNo());
			ArrayList<DeviceInfo> deviceInfos = this.searchMapper.selectDeviceInfos(deviceInfo);
			
			if (deviceInfos.size() != 1) {
				message +="</br>기기 정보 부재로 Push Message 발송하지 않음";
			}else {
				/*
				 * 푸시 알림 설정 확인
				 * */
				MemberConfig memberConfig = new MemberConfig();
				memberConfig.setMemberNo(req.getMemberNo());
				ArrayList<MemberConfig> memberConfigs = this.searchService.selectMemberConfigs(memberConfig);
				
				/*푸쉬 알림 설정이 존재하지 않는 다면, 기본 OFF 로 인서트*/ 
				boolean push = false;
				if (memberConfigs.size()< 1) {
					memberConfig.setDevicePush("N");
					memberConfig.setEmailReceive("N");
					this.memberConfigMapper.insert(memberConfig);
					push = false;
				}else {
					memberConfig = memberConfigs.get(0);
					push = memberConfig.getDevicePush().equals("Y") ? true : false;;
				}
				
				String pushReturn = "";
				deviceInfo = deviceInfos.get(0);
				
				if (!push) {
					message += "</br> 알림 설정이 OFF 로 Push Message 발송않함";
				}else {
					
					/* 선택한 상품권 푸시 발송*/
					if (deviceInfo.getOs().equalsIgnoreCase("android")) {
						pushReturn = androidPushService.pushPointCode(deviceInfo, pointCodeIssue);
					}else  if (deviceInfo.getOs().equalsIgnoreCase("apple")) {
						pushReturn = iosPushService.pushPointCode(deviceInfo, pointCodeIssue);
					}
					
					if (pushReturn == null) {
						message += "</br> Push Message  발송 실패";
					}else {
						message += "</br> Push Message 발송 완료";
					}
				}
			}
			
			ResponseUtil.setSuccessResponse(res, "100" , message);
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "포인트 코드 발급 에러");
			return res;
		}
	}

	
	// --------------------------------------------------------------------------------------------------------------------
	// 포인트 코드 서비스 메서드 
	// --------------------------------------------------------------------------------------------------------------------
	
	@Override
	public ReturnpBaseResponse selectPointCodeReports(HashMap<String, Object> dbParams) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pcr = this.mainMapper.selectPointCodeIssueReports(dbParams);
			res.setRows(pcr);
			res.setTotal(	pcr.size());
			ResponseUtil.setSuccessResponse(res, "100" , "조회 성공");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "조회 에러 ");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse selectPeriodPointCodeIssueReports(HashMap<String, Object> dbParams) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pcr = this.mainMapper.selectPeriodPointCodeIssueReports(dbParams);
			res.setRows(pcr);
			res.setTotal(	pcr.size());
			ResponseUtil.setSuccessResponse(res, "100" , "조회 성공");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "조회 에러 ");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse loadPointCodeIssues(HashMap<String, Object> params) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pointCodeIssues = this.mainMapper.loadPointCodeIssues(params);
			res.setRows(pointCodeIssues);
			res.setTotal(	this.searchService.selectTotalRecords());
			ResponseUtil.setSuccessResponse(res, "100" , "조회 성공");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "서버 에러 ");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse chanagePointCodeIssueStatus(PointCodeIssue pointCodeIssue) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			PointCodeIssue pc = this.pointCodeIssueMapper.selectByPrimaryKey(pointCodeIssue.getPointCodeIssueNo());
			if (pc == null){
				ResponseUtil.setResponse(res, "1008", "잘못된 요청 - 해당 포인트 코드가 존재하지 않습니다..");
				throw new ReturnpException(res);
			}
		
			this.pointCodeIssueMapper.updateByPrimaryKeySelective(pointCodeIssue);
			ResponseUtil.setSuccessResponse(res, "100" , "포인트 코드  상태 변경 완료");
			return res;
		}catch(ReturnpException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return e.getBaseResponse();
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "포인트 코드 상태 변경 변경 실패");
			return res;
		}
	}

	// --------------------------------------------------------------------------------------------------------------------
	// 포인트 코드 적립 트랜잭션 서비스 메서드 
	// --------------------------------------------------------------------------------------------------------------------
	
	@Override
	public ReturnpBaseResponse selectPointCodeTransactionReports(HashMap<String, Object> dbParams) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pcr = this.mainMapper.selectPointCodeTransactionReports(dbParams);
			res.setRows(pcr);
			res.setTotal(	pcr.size());
			ResponseUtil.setSuccessResponse(res, "100" , "조회 성공");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "조회 에러 ");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse selectPeriodPointCodeTransactionReports(HashMap<String, Object> dbParams) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pcr = this.mainMapper.selectPeriodPointCodeTransactionReports(dbParams);
			res.setRows(pcr);
			res.setTotal(	pcr.size());
			ResponseUtil.setSuccessResponse(res, "100" , "조회 성공");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "조회 에러 ");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse loadPointCodeTransactions(HashMap<String, Object> params) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pointCodeIssues = this.mainMapper.loadPointCodeTransactions(params);
			res.setRows(pointCodeIssues);
			res.setTotal(	this.searchService.selectTotalRecords());
			ResponseUtil.setSuccessResponse(res, "100" , "조회 성공");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "서버 에러 ");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse selectPointCodePointbackRecords(HashMap<String, Object> param) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pointCodeIssues = this.mainMapper.selectPointCodePointbackRecords(param);
			res.setRows(pointCodeIssues);
			res.setTotal(	this.searchService.selectTotalRecords());
			ResponseUtil.setSuccessResponse(res, "100" , "조회 성공");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "서버 에러 ");
			return res;
		}
	}


}
