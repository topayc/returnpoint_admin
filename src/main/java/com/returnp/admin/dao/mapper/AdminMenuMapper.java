package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.AdminMenu;

public interface AdminMenuMapper {
    int deleteByPrimaryKey(Integer adminMenuNo);

    int insert(AdminMenu record);

    int insertSelective(AdminMenu record);

    AdminMenu selectByPrimaryKey(Integer adminMenuNo);

    int updateByPrimaryKeySelective(AdminMenu record);

    int updateByPrimaryKey(AdminMenu record);
}