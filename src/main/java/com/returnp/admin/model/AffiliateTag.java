package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;

public class AffiliateTag extends QueryCondition {
    private Integer affiliateTagNo;

    private Integer affiliateNo;

    private String affiliateTag;

    private String affiliateTagStatus;

    public Integer getAffiliateTagNo() {
        return affiliateTagNo;
    }

    public void setAffiliateTagNo(Integer affiliateTagNo) {
        this.affiliateTagNo = affiliateTagNo;
    }

    public Integer getAffiliateNo() {
        return affiliateNo;
    }

    public void setAffiliateNo(Integer affiliateNo) {
        this.affiliateNo = affiliateNo;
    }

    public String getAffiliateTag() {
        return affiliateTag;
    }

    public void setAffiliateTag(String affiliateTag) {
        this.affiliateTag = affiliateTag == null ? null : affiliateTag.trim();
    }

    public String getAffiliateTagStatus() {
        return affiliateTagStatus;
    }

    public void setAffiliateTagStatus(String affiliateTagStatus) {
        this.affiliateTagStatus = affiliateTagStatus == null ? null : affiliateTagStatus.trim();
    }
}