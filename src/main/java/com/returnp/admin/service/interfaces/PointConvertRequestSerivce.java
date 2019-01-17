package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.model.PointConvertRequest;

@Transactional
public interface PointConvertRequestSerivce {
	  int deleteByPrimaryKey(Integer pointConvertRequestNo);

	    int insert(PointConvertRequest record);

	    int insertSelective(PointConvertRequest record);

	    PointConvertRequest selectByPrimaryKey(Integer pointConvertRequestNo);

	    int updateByPrimaryKeySelective(PointConvertRequest record);

	    int updateByPrimaryKey(PointConvertRequest record);
}
