package com.returnp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.MembershipRequestMapper;
import com.returnp.admin.model.MembershipRequest;
import com.returnp.admin.service.interfaces.MembershipRequestService;

@Service
public class MembershipRequestServiceImpl implements MembershipRequestService {

	@Autowired MembershipRequestMapper membershipRequestMapper;
	@Override
	public int deleteByPrimaryKey(Integer membershipRequestNo) {
		return this.membershipRequestMapper.deleteByPrimaryKey(membershipRequestNo);
	}

	@Override
	public int insert(MembershipRequest record) {
		// TODO Auto-generated method stub
		return this.membershipRequestMapper.insert(record);
	}

	@Override
	public int insertSelective(MembershipRequest record) {
		// TODO Auto-generated method stub
		return this.membershipRequestMapper.insertSelective(record);
	}

	@Override
	public MembershipRequest selectByPrimaryKey(Integer membershipRequestNo) {
		// TODO Auto-generated method stub
		return this.membershipRequestMapper.selectByPrimaryKey(membershipRequestNo);
	}

	@Override
	public int updateByPrimaryKeySelective(MembershipRequest record) {
		// TODO Auto-generated method stub
		return this.membershipRequestMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MembershipRequest record) {
		// TODO Auto-generated method stub
		return this.membershipRequestMapper.updateByPrimaryKeySelective(record);
	}

}
