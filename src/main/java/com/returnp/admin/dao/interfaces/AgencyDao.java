package com.returnp.admin.dao.interfaces;

import com.returnp.admin.model.Agency;

public interface AgencyDao {
	   int deleteByPrimaryKey(Integer agencyNo);

	    int insert(Agency record);

	    int insertSelective(Agency record);

	    Agency selectByPrimaryKey(Integer agencyNo);

	    int updateByPrimaryKeySelective(Agency record);

	    int updateByPrimaryKey(Agency record);
}
