package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.AffiliateCategory;

public interface AffiliateCategoryMapper {
    int deleteByPrimaryKey(Integer affiliateCategoryNo);

    int insert(AffiliateCategory record);

    int insertSelective(AffiliateCategory record);

    AffiliateCategory selectByPrimaryKey(Integer affiliateCategoryNo);

    int updateByPrimaryKeySelective(AffiliateCategory record);

    int updateByPrimaryKey(AffiliateCategory record);
}