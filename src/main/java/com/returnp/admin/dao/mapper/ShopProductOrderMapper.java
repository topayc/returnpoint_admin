package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.ShopProductOrder;
import com.returnp.admin.model.ShopProductOrderKey;

public interface ShopProductOrderMapper {
    int deleteByPrimaryKey(ShopProductOrderKey key);

    int insert(ShopProductOrder record);

    int insertSelective(ShopProductOrder record);

    ShopProductOrder selectByPrimaryKey(ShopProductOrderKey key);

    int updateByPrimaryKeySelective(ShopProductOrder record);

    int updateByPrimaryKey(ShopProductOrder record);
}