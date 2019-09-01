package com.returnp.admin.service.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.AffiliateTid;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.MemberBankAccount;

@Transactional
public interface QueryService {
	int deleteAffiliateTid(AffiliateTid affiliateTid);

	int deleteGPoint(GreenPoint gPoint);
	
	int updateMemberBankAccount(MemberBankAccount account);


	
}
