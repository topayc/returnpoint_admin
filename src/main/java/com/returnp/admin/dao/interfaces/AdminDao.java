package com.returnp.admin.dao.interfaces;

import com.returnp.admin.model.Admin;

public interface AdminDao {
	 int deleteByPrimaryKey(Integer adminNo);

	    int insert(Admin record);

	    int insertSelective(Admin record);

	    Admin selectByPrimaryKey(Integer adminNo);

	    int updateByPrimaryKeySelective(Admin record);

	    int updateByPrimaryKey(Admin record);
}
