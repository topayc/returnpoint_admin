package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.Category;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer categoryNo);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer categoryNo);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}