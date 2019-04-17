package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.MyGiftCard;

public interface MyGiftCardMapper {
    int deleteByPrimaryKey(Integer myGiftCardNo);

    int insert(MyGiftCard record);

    int insertSelective(MyGiftCard record);

    MyGiftCard selectByPrimaryKey(Integer myGiftCardNo);

    int updateByPrimaryKeySelective(MyGiftCard record);

    int updateByPrimaryKey(MyGiftCard record);
}