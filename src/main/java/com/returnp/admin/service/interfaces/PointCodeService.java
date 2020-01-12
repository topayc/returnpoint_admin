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
public interface PointCodeService {

	ReturnpBaseResponse selectPointCodeIssueRequestReports(HashMap<String, Object> dbParams);

	ReturnpBaseResponse selectPeriodPointCodeIssueRequestReports(HashMap<String, Object> dbParams);

	ReturnpBaseResponse chanagePointCodeRequestStatus(PointCodeIssueRequest pointCodeIssueRequest);

	ReturnpBaseResponse issuePointCode(PointCodeIssue pointCodeIssue , boolean isPush);

	ReturnpBaseResponse selectPointCodeReports(HashMap<String, Object> dbParams);

	ReturnpBaseResponse selectPeriodPointCodeIssueReports(HashMap<String, Object> dbParams);

	ReturnpBaseResponse loadPointCodeIssueRequests(HashMap<String, Object> params);

	ReturnpBaseResponse loadPointCodeIssues(HashMap<String, Object> params);

	ReturnpBaseResponse chanagePointCodeIssueStatus(PointCodeIssue pointCodeIssue);

	ReturnpBaseResponse selectPointCodeTransactionReports(HashMap<String, Object> dbParams);

	ReturnpBaseResponse selectPeriodPointCodeTransactionReports(HashMap<String, Object> dbParams);

	ReturnpBaseResponse loadPointCodeTransactions(HashMap<String, Object> params);

	ReturnpBaseResponse selectPointCodePointbackRecords(HashMap<String, Object> param);

	ReturnpBaseResponse chanagePointCodeRequestsStatus(ArrayList<Integer> pointCodeIssueRequestNos, String status);

	ReturnpBaseResponse issuePointCodes(ArrayList<String> issueRequests);


}
