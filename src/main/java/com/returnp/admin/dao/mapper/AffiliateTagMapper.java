package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.AffiliateTag;

public interface AffiliateTagMapper {
    int deleteByPrimaryKey(Integer affiliateTagNo);

    int insert(AffiliateTag record);

    int insertSelective(AffiliateTag record);

    AffiliateTag selectByPrimaryKey(Integer affiliateTagNo);

    int updateByPrimaryKeySelective(AffiliateTag record);

    int updateByPrimaryKey(AffiliateTag record);
}