package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.model.GreenPoint;

@Transactional
public interface GreenPointService {
	  int deleteByPrimaryKey(Integer greenPointNo);

	    int insert(GreenPoint record);

	    int insertSelective(GreenPoint record);

	    GreenPoint selectByPrimaryKey(Integer greenPointNo);

	    int updateByPrimaryKeySelective(GreenPoint record);

	    int updateByPrimaryKey(GreenPoint record);
}
