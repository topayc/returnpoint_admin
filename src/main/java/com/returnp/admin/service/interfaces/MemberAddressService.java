package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.model.MemberAddress;

@Transactional
public interface MemberAddressService {
	 int deleteByPrimaryKey(Integer memberAddressNo);

	    int insert(MemberAddress record);

	    int insertSelective(MemberAddress record);

	    MemberAddress selectByPrimaryKey(Integer memberAddressNo);

	    int updateByPrimaryKeySelective(MemberAddress record);

	    int updateByPrimaryKey(MemberAddress record);
	    
	
}
