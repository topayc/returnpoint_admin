package com.returnp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.PolicyMapper;
import com.returnp.admin.model.Policy;
import com.returnp.admin.service.interfaces.PolicyService;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired PolicyMapper policyMapper;
	
	@Override
	public int insert(Policy policy) {
		return this.policyMapper.insert(policy);
	}
}
