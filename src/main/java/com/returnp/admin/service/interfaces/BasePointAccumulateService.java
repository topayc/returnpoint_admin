package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.common.DataMap;
import com.returnp.admin.common.ReturnpException;
import com.returnp.admin.dto.command.InnerPointBackTarget;
import com.returnp.admin.dto.command.OuterPointBackTarget;
import com.returnp.admin.dto.command.PointBackTarget;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.Affiliate;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.PaymentTransaction;


@Transactional
public interface BasePointAccumulateService {
	public ReturnpBaseResponse accumulate(DataMap dataMap) throws ReturnpException, Exception;

	public ReturnpBaseResponse cancelAccumulate(DataMap dataMap);

	public void restorePoint(DataMap dataMap) throws ReturnpException;
	
	public ReturnpBaseResponse   accumuatePoint(int paymentTransactioinNo) throws ReturnpException;
	
	public void accumuatePoint(PaymentTransaction paymentTransaction) throws ReturnpException;

	public ReturnpBaseResponse   accumuatePoint(String paymentApprovalNumber) throws ReturnpException;
	
	public PaymentTransaction  createPaymentTransaction(DataMap dataMap) throws ReturnpException;
	
	public void  increasePoint(PaymentTransaction transaction, int memberNo, int nodeNo, String nodeType, String nodeTypeName, float accRate) throws ReturnpException;

	public GreenPoint  createRecommenderRPoint(int memberNo);

	public PointBackTarget findInnerPointBackFindTarget(PointBackTarget target);
	
	public OuterPointBackTarget findOuterPointBackTarget(OuterPointBackTarget target);
	
	public InnerPointBackTarget findInnerPointBackTarget(String affiliateCode);

	public ReturnpBaseResponse cancelAccumuate(String pan);

	public ReturnpBaseResponse cancelAccumuate(int paymentTrasactionNo);
	
	public Member validateMemberAuth(String MemberEmail, String phoneNumber, String phoneNumberCountry) throws ReturnpException;

	public Affiliate validateAffiliateAuth(String afId) throws ReturnpException;

	public PaymentTransaction  validate(String pan, String pas) throws ReturnpException;
	
	public DataMap convertPaymentTransactionToDataMap(PaymentTransaction pt);
	
}
