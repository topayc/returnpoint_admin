package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;

public class AffiliateCategory extends QueryCondition {
    private Integer affiliateCategoryNo;

    private Integer affiliateNo;

    private Integer category1No;

    private Integer category2No;

    public Integer getAffiliateCategoryNo() {
        return affiliateCategoryNo;
    }

    public void setAffiliateCategoryNo(Integer affiliateCategoryNo) {
        this.affiliateCategoryNo = affiliateCategoryNo;
    }

    public Integer getAffiliateNo() {
        return affiliateNo;
    }

    public void setAffiliateNo(Integer affiliateNo) {
        this.affiliateNo = affiliateNo;
    }

    public Integer getCategory1No() {
        return category1No;
    }

    public void setCategory1No(Integer category1No) {
        this.category1No = category1No;
    }

    public Integer getCategory2No() {
        return category2No;
    }

    public void setCategory2No(Integer category2No) {
        this.category2No = category2No;
    }
}