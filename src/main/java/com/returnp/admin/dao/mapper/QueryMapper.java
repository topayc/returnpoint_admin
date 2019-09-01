package com.returnp.admin.dao.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.returnp.admin.model.AffiliateTid;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.MemberBankAccount;

public interface QueryMapper {
	int deleteAffiliateTid(AffiliateTid affiliateTid);
	int deleteGPoint(GreenPoint gPoint);
	int updateMemberBankAccount(MemberBankAccount account);
}
