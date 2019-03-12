package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.Product;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer productNo);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer productNo);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}