package com.returnp.admin.service.interfaces;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.model.AdminRole;

@Transactional
public interface AdminRoleService {
	
	int deleteByPrimaryKey(Integer adminRoleNo);

    int insert(AdminRole record);

    int insertSelective(AdminRole record);

    AdminRole selectByPrimaryKey(Integer adminRoleNo);
    
    ArrayList<AdminRole> selectByAdminNo(Integer adminRoleNo);

    int updateByPrimaryKeySelective(AdminRole record);

    int updateByPrimaryKey(AdminRole record);
}	
