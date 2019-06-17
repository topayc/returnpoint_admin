package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.AffiliatePaymentRouter;

public interface AffiliatePaymentRouterMapper {
    int deleteByPrimaryKey(Integer affiliatePaymentRouterNo);

	int insert(AffiliatePaymentRouter record);

	int insertSelective(AffiliatePaymentRouter record);

	AffiliatePaymentRouter selectByPrimaryKey(Integer affiliatePaymentRouterNo);

	int updateByPrimaryKeySelective(AffiliatePaymentRouter record);

	int updateByPrimaryKey(AffiliatePaymentRouter record);



}