package com.returnp.admin.dao.interfaces;

import org.springframework.stereotype.Repository;

import com.returnp.admin.model.MemberAddress;

@Repository
public interface MemberAddressDao {
	 int deleteByPrimaryKey(Integer memberAddressNo);

	    int insert(MemberAddress record);

	    int insertSelective(MemberAddress record);

	    MemberAddress selectByPrimaryKey(Integer memberAddressNo);

	    int updateByPrimaryKeySelective(MemberAddress record);

	    int updateByPrimaryKey(MemberAddress record);
}
