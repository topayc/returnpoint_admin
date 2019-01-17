package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.model.RedPoint;

@Transactional
public interface RedPointService {
	int deleteByPrimaryKey(Integer redPointNo);

    int insert(RedPoint record);

    int insertSelective(RedPoint record);

    RedPoint selectByPrimaryKey(Integer redPointNo);

    int updateByPrimaryKeySelective(RedPoint record);

    int updateByPrimaryKey(RedPoint record);
}
