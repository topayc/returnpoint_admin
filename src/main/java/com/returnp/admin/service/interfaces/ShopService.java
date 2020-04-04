package com.returnp.admin.service.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.common.ReturnpException;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.GiftCardOrder;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.PointCodeIssue;
import com.returnp.admin.model.PointCodeIssueRequest;
import com.returnp.admin.model.PointCoupon;
import com.returnp.admin.model.PointCouponPointbackRecord;
import com.returnp.admin.model.PointCouponTransaction;

@Transactional
public interface ShopService {

	ReturnpBaseResponse selectOrderReports(HashMap<String, Object> dbParams);

	ReturnpBaseResponse selectPeriodOrderReports(HashMap<String, Object> dbParams);

	ReturnpBaseResponse loadOrders(HashMap<String, Object> params);

	ReturnpBaseResponse deleteOrder(HashMap<String, Object> params);


}
