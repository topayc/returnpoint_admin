package com.returnp.admin.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.SplittableRandom;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.AppConstants;
import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.common.ReturnpException;
import com.returnp.admin.dao.mapper.MainMapper;
import com.returnp.admin.dao.mapper.PointCouponMapper;
import com.returnp.admin.dao.mapper.PointCouponTransactionMapper;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.PointCoupon;
import com.returnp.admin.model.PointCouponPointbackRecord;
import com.returnp.admin.model.PointCouponTransaction;
import com.returnp.admin.service.interfaces.PointCouponService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class PointCouponServiceImp implements PointCouponService{
	
	@Autowired PointCouponMapper pointCouponMapper;
	@Autowired PointCouponTransactionMapper pointCouponTransactionMapper;
	@Autowired MainMapper mainMapper;
	@Autowired SearchService searchService;;
	
	@Override
	public ReturnpBaseResponse createPointCoupon(PointCoupon pointCoupon, int issueCount, HttpSession session) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			char[] PIN_CHARACTERS  = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
			SplittableRandom splittableRandom = null;
			char[] pinCharArrs = new char[20];
			AdminSession adminSession = (AdminSession)session.getAttribute(AppConstants.ADMIN_SESSION);
			float accRate = pointCoupon.getAccPointRate() / 100;
			for (int i = 0; i <issueCount; i++) {
				Collections.shuffle(Arrays.asList(PIN_CHARACTERS));
				splittableRandom = new SplittableRandom();
				for (int k = 0; k < pinCharArrs.length; k++) {
					int elementIndex = splittableRandom.nextInt(PIN_CHARACTERS.length);
					pinCharArrs[k] = PIN_CHARACTERS[elementIndex];
				}
				pointCoupon.setPointCouponNo(null);
				pointCoupon.setAccPointRate(accRate);
				pointCoupon.setCouponNumber(String.valueOf(pinCharArrs));
				pointCoupon.setCouponType("1");
				pointCoupon.setUseStatus("1");
				pointCoupon.setDeliveryStatus("N");
				pointCoupon.setPublisher(adminSession.getAdminName());
				this.pointCouponMapper.insert(pointCoupon);
			}
			ResponseUtil.setSuccessResponse(res, "100" , "포인트 쿠폰 생성 완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "포인트 쿠폰 생성 실패");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse chanagePointCoupon(PointCoupon pointCoupon) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			if (pointCoupon.getPointCouponNo() == null  || pointCoupon.getCouponNumber() == null) {
				ResponseUtil.setResponse(res, "1032", "잘못된 요청 - 필수 파라메터가 없습니다");
				throw new ReturnpException(res);
			}
			
			PointCoupon coupon = this.pointCouponMapper.selectByPrimaryKey(pointCoupon.getPointCouponNo());
				
			if (coupon == null ||  !coupon.getCouponNumber().equals(pointCoupon.getCouponNumber())){
				ResponseUtil.setResponse(res, "1008", "잘못된 요청 - 해당 포인트 쿠폰이 존재하지 않습니다");
				throw new ReturnpException(res);
			}
			
			HashMap<String, Object> dbParamMap = new HashMap<String, Object>();
			dbParamMap.put("pointCouponNo", pointCoupon.getPointCouponNo());
			dbParamMap.put("couponNumber", pointCoupon.getCouponNumber());
			ArrayList<HashMap<String, Object>> trans = this.searchService.selectPointCouponTransactions(dbParamMap);
			
			if (trans.size() > 0) {
				if (pointCoupon.getUseStatus() != null && !pointCoupon.getUseStatus().equals("3")) {
					ResponseUtil.setResponse(res, "1065", "잘못된 요청 - 이미 적립이 완료된 코드로 ' 적립 코드 상태' 를 변경할 수 없습니다 ");
					throw new ReturnpException(res);
				}
				if (pointCoupon.getDeliveryStatus()!= null) {
					ResponseUtil.setResponse(res, "1066", "잘못된 요청 - 이미 적립이 완료된 코드로 ' 전시 상태' 를 변경할 수 없습니다 ");
					throw new ReturnpException(res);
				}
			}else {
				if (pointCoupon.getUseStatus() != null && pointCoupon.getUseStatus().equals("3")) {
					ResponseUtil.setResponse(
						res, 
						"10651", 
						"잘못된 요청 - 적립이 되지 않는 유효한 코드 입니다. .</br>임의로 사용 완료로 변경할 수 없습니다.</br>사용완료 상태는 사용자가 적립코드를 등록했을때 자동으로 변경됩니다. ");
					throw new ReturnpException(res);
				}
				
		/*		if (pointCoupon.getDeliveryStatus()!= null && pointCoupon.getDeliveryStatus().equals("Y")) {
					ResponseUtil.setResponse(
						res,
						"1066", 
						"잘못된 요청 - 적립이 되지 않는 유효한 코드 입니다. 임의로 전시상태를 변경할 수 없습니다.</br>사용완료 상태는 사용자가 적립코드를 등록했을때 자동으로 변경됩니다.");
					throw new ReturnpException(res);
				}*/
			}
		
			this.pointCouponMapper.updateByPrimaryKeySelective(pointCoupon);
			ResponseUtil.setSuccessResponse(res, "100" , "포인트 쿠폰 상태 변경 완료");
			return res;
		}catch(ReturnpException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return e.getBaseResponse();
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "포인트 쿠폰 변경 실패");
			return res;
		}
	}


	@Override
	public ReturnpBaseResponse selectPointCouponReports(HashMap<String, Object> dbParams) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pcr = this.mainMapper.selectPointCouponReports(dbParams);
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
	public ReturnpBaseResponse selectPeriodPointCouponReports(HashMap<String, Object> dbParams) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pcr = this.mainMapper.selectPeriodPointCouponReports(dbParams);
			res.setRows(pcr);
			res.setTotal(pcr.size());
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
	public ReturnpBaseResponse loadPointCoupons(HashMap<String, Object> dbParams) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pointCoupons = this.mainMapper.loadPointCoupons(dbParams);
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
	
	
	////포인트 적립 트랜 잭션 /////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public ReturnpBaseResponse selectPointCouponTransactionReports(HashMap<String, Object> dbParams) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pcrs = this.mainMapper.selectPointCouponTransactionReports(dbParams);
			res.setRows(pcrs);
			res.setTotal(pcrs.size());
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
	public ReturnpBaseResponse selectPeriodPointCouponTransactionReportsReports(HashMap<String, Object> dbParams) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pcrs = this.mainMapper.selectPeriodPointCouponTransactionReportsReports(dbParams);
			res.setRows(pcrs);
			res.setTotal(	this.searchService.selectTotalRecords());
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
	public ReturnpBaseResponse loadPointCouponTransactions(HashMap<String, Object> dbParams) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pcrs = this.mainMapper.loadPointCouponTransactions(dbParams);
			res.setRows(pcrs);
			res.setTotal(	this.searchService.selectTotalRecords());
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
	public ReturnpBaseResponse selectPointCouponPointbackRecords(HashMap<String, Object> param) {
		ArrayListResponse<HashMap<String, Object>> res = new ArrayListResponse<HashMap<String, Object>>();
		try {
			ArrayList<HashMap<String, Object>> pcrs = this.searchService.selectPointCouponPointbackRecords(param);
			res.setRows(pcrs);
			res.setTotal(	this.searchService.selectTotalRecords());
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
	public ReturnpBaseResponse deletePointCoupon(HashMap<String, Object> param) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		
		try {
			if (!param.containsKey("pointCouponNo")  || !param.containsKey("couponNumber")) {
				ResponseUtil.setResponse(res, "1030", "잘못된 요청 - 필수 파라메터가 없습니다");
				throw new ReturnpException(res);
			}
			
			PointCoupon coupon = this.pointCouponMapper.selectByPrimaryKey(Integer.parseInt((String)param.get("pointCouponNo")));
			if (coupon == null || !coupon.getCouponNumber().equals((String)param.get("couponNumber"))) {
				ResponseUtil.setResponse(res, "1020", "잘못된 요청 - 잘못된 포인트 적립코드 고유 번호입니다");
				throw new ReturnpException(res);
			}
			
			ArrayList<HashMap<String, Object>> trans = this.searchService.selectPointCouponTransactions(param);
			if (trans.size() > 0) {
				ResponseUtil.setResponse(res, "1027", "해당 적립 코드는 이미 적립 처리가 완료된 코드입니다. 삭제할 수 없습니다");
				throw new ReturnpException(res);
			}
			
			this.pointCouponMapper.deleteByPrimaryKey(Integer.parseInt((String)param.get("pointCouponNo")));
			ResponseUtil.setSuccessResponse(res, "100" , "삭제 성공");
			return res;
		}catch(ReturnpException e) {
			e.printStackTrace();
			if (!TransactionAspectSupport.currentTransactionStatus().isRollbackOnly()) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
			res = e.getBaseResponse();
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "서버 에러 ");
			return res;
		}
	}
	
	@Override
	public ReturnpBaseResponse createPointCouponTransaction(PointCouponTransaction pointCouponTransaction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnpBaseResponse createPointCouponPointbackRecord(PointCouponPointbackRecord pointCouponPointbackRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnpBaseResponse removePointCoupon(PointCoupon pointCoupon, int issueCount, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
