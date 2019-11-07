package com.returnp.admin.service.interfaces;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.common.ReturnpException;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCardOrder;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.PointCoupon;
import com.returnp.admin.model.PointCouponPointbackRecord;
import com.returnp.admin.model.PointCouponTransaction;

@Transactional
public interface PointCouponService {
	public ReturnpBaseResponse createPointCoupon(PointCoupon pointCoupon, int issueCount, HttpSession session);
	public ReturnpBaseResponse chanagePointCoupon(PointCoupon pointCoupon);
	public ReturnpBaseResponse removePointCoupon(PointCoupon pointCoupon, int issueCount, HttpSession session);
	public ReturnpBaseResponse createPointCouponTransaction(PointCouponTransaction pointCouponTransaction);
	public ReturnpBaseResponse createPointCouponPointbackRecord(PointCouponPointbackRecord pointCouponPointbackRecord);
	public ReturnpBaseResponse selectPointCouponReports(HashMap<String, Object> dbParams);
	public ReturnpBaseResponse selectPeriodPointCouponReports(HashMap<String, Object> dbParams);
	public ReturnpBaseResponse loadPointCoupons(HashMap<String, Object> dbParams);
	public ReturnpBaseResponse loadPointCouponTransactions(HashMap<String, Object> dbParams);
	public ReturnpBaseResponse selectPeriodPointCouponTransactionReportsReports(HashMap<String, Object> dbParams);
	public ReturnpBaseResponse selectPointCouponTransactionReports(HashMap<String, Object> dbParams);
	public ReturnpBaseResponse selectPointCouponPointbackRecords(HashMap<String, Object> param);
	public ReturnpBaseResponse deletePointCoupon(HashMap<String, Object> param);
}
