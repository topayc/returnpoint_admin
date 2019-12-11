package com.returnp.admin.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.common.ReturnpException;
import com.returnp.admin.dao.mapper.MainMapper;
import com.returnp.admin.dao.mapper.PointCodeIssueMapper;
import com.returnp.admin.dao.mapper.PointCodeIssueRequestMapper;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.PointCodeIssue;
import com.returnp.admin.model.PointCodeIssueRequest;
import com.returnp.admin.service.interfaces.PointCodeService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class PointCodeServiceImp implements PointCodeService{
	@Autowired MainMapper mainMapper;
	@Autowired PointCodeIssueRequestMapper pointCodeIssueRequestMapper  ;
	@Autowired PointCodeIssueMapper pointCodeIssueMapper  ;
	@Autowired SearchService searchService;;
	
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
		try {
			pointCodeIssue.setUseStatus("1");
			this.pointCodeIssueMapper.insert(pointCodeIssue);
			ResponseUtil.setSuccessResponse(res, "100" , "발급 성공");
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

}
