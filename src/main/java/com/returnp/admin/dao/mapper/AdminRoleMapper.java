package com.returnp.admin.dao.mapper;

import java.util.ArrayList;

import com.returnp.admin.model.AdminRole;

public interface AdminRoleMapper {
    int deleteByPrimaryKey(Integer adminRoleNo);

    int insert(AdminRole record);

    int insertSelective(AdminRole record);

    AdminRole selectByPrimaryKey(Integer adminRoleNo);
    
    ArrayList<AdminRole> selectByAdminNo(Integer adminRoleNo);
    
    int updateByPrimaryKeySelective(AdminRole record);

    int updateByPrimaryKey(AdminRole record);
}