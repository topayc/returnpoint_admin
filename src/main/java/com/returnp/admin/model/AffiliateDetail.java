package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class AffiliateDetail extends QueryCondition {
    private Integer affiliateDetailNo;

    private Integer affiliateNo;

    private String businessNumber;

    private String businessItem;

    private String buisnessName;

    private String businessType;

    private String overview;

    private String afffiliateNotice;

    private String holiday;

    private String openingHours;

    private String commonWeb;

    private String uflatCardWeb;

    private String etc;

    private Date createTime;

    private Date updateTime;

    public Integer getAffiliateDetailNo() {
        return affiliateDetailNo;
    }

    public void setAffiliateDetailNo(Integer affiliateDetailNo) {
        this.affiliateDetailNo = affiliateDetailNo;
    }

    public Integer getAffiliateNo() {
        return affiliateNo;
    }

    public void setAffiliateNo(Integer affiliateNo) {
        this.affiliateNo = affiliateNo;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber == null ? null : businessNumber.trim();
    }

    public String getBusinessItem() {
        return businessItem;
    }

    public void setBusinessItem(String businessItem) {
        this.businessItem = businessItem == null ? null : businessItem.trim();
    }

    public String getBuisnessName() {
        return buisnessName;
    }

    public void setBuisnessName(String buisnessName) {
        this.buisnessName = buisnessName == null ? null : buisnessName.trim();
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview == null ? null : overview.trim();
    }

    public String getAfffiliateNotice() {
        return afffiliateNotice;
    }

    public void setAfffiliateNotice(String afffiliateNotice) {
        this.afffiliateNotice = afffiliateNotice == null ? null : afffiliateNotice.trim();
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday == null ? null : holiday.trim();
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours == null ? null : openingHours.trim();
    }

    public String getCommonWeb() {
        return commonWeb;
    }

    public void setCommonWeb(String commonWeb) {
        this.commonWeb = commonWeb == null ? null : commonWeb.trim();
    }

    public String getUflatCardWeb() {
        return uflatCardWeb;
    }

    public void setUflatCardWeb(String uflatCardWeb) {
        this.uflatCardWeb = uflatCardWeb == null ? null : uflatCardWeb.trim();
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc == null ? null : etc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}