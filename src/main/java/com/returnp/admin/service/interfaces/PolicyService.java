package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.model.Policy;

@Transactional
public interface PolicyService {
	public int insert(Policy policy); 
}
