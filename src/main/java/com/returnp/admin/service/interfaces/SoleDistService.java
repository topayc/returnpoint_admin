package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.model.SoleDist;

@Transactional
public interface SoleDistService {
	int deleteByPrimaryKey(Integer soleDistNo);

    int insert(SoleDist record);

    int insertSelective(SoleDist record);

    SoleDist selectByPrimaryKey(Integer soleDistNo);

    int updateByPrimaryKeySelective(SoleDist record);

    int updateByPrimaryKey(SoleDist record);
}	
