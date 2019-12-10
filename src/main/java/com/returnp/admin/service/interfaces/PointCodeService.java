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
public interface PointCodeService {

	ReturnpBaseResponse selectPointCodeReports(HashMap<String, Object> dbParams);

	ReturnpBaseResponse selectPeriodPointCodeReports(HashMap<String, Object> dbParams);
}
