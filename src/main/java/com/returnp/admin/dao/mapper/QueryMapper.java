package com.returnp.admin.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.returnp.admin.model.AffiliateTid;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.MemberBankAccount;

public interface QueryMapper {
	int deleteAffiliateTid(AffiliateTid affiliateTid);
	int deleteGPoint(GreenPoint gPoint);
	int updateMemberBankAccount(MemberBankAccount account);
}
