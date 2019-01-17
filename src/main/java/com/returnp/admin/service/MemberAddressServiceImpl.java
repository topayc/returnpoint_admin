package com.returnp.admin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.MemberAddressMapper;
import com.returnp.admin.model.MemberAddress;
import com.returnp.admin.service.interfaces.MemberAddressService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class MemberAddressServiceImpl implements MemberAddressService {

	@Autowired MemberAddressMapper memberMapper;
	@Autowired SearchService searchService;

	@Override
	public int deleteByPrimaryKey(Integer memberAddressNo) {
		// TODO Auto-generated method stub
		return this.memberMapper.deleteByPrimaryKey(memberAddressNo);
	}

	@Override
	public int insert(MemberAddress record) {
		// TODO Auto-generated method stub
		return this.memberMapper.insert(record);
	}

	@Override
	public int insertSelective(MemberAddress record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberAddress selectByPrimaryKey(Integer memberAddressNo) {
		// TODO Auto-generated method stub
		return this.memberMapper.selectByPrimaryKey(memberAddressNo);
	}

	@Override
	public int updateByPrimaryKeySelective(MemberAddress record) {
		// TODO Auto-generated method stub
		return this.memberMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MemberAddress record) {
		// TODO Auto-generated method stub
		return this.memberMapper.updateByPrimaryKey(record);
	}


}
