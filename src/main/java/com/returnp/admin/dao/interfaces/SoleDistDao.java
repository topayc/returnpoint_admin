package com.returnp.admin.dao.interfaces;

import com.returnp.admin.model.SoleDist;

public interface SoleDistDao {
	int deleteByPrimaryKey(Integer soleDistNo);

    int insert(SoleDist record);

    int insertSelective(SoleDist record);

    SoleDist selectByPrimaryKey(Integer soleDistNo);

    int updateByPrimaryKeySelective(SoleDist record);

    int updateByPrimaryKey(SoleDist record);
}
