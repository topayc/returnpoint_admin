package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.AdminFile;

public interface AdminFileMapper {
    int deleteByPrimaryKey(Integer adminFileNo);

    int insert(AdminFile record);

    int insertSelective(AdminFile record);

    AdminFile selectByPrimaryKey(Integer adminFileNo);

    int updateByPrimaryKeySelective(AdminFile record);

    int updateByPrimaryKey(AdminFile record);
}