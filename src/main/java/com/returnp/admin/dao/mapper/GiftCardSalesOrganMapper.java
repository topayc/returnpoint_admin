package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.GiftCardSalesOrgan;

public interface GiftCardSalesOrganMapper {
    int deleteByPrimaryKey(Integer giftCardSalesOrganNo);

    int insert(GiftCardSalesOrgan record);

    int insertSelective(GiftCardSalesOrgan record);

    GiftCardSalesOrgan selectByPrimaryKey(Integer giftCardSalesOrganNo);

    int updateByPrimaryKeySelective(GiftCardSalesOrgan record);

    int updateByPrimaryKey(GiftCardSalesOrgan record);
}