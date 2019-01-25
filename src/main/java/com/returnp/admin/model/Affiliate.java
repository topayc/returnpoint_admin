package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class Affiliate extends QueryCondition {
    public Integer affiliateNo;

    public String affiliateSerial;

    public Float affiliateComm;

    public String affiliateCode;

    public String affiliateName;

    public String affiliateAddress;

    public String affiliateTel;

    public String affiliatePhone;

    public Integer memberNo;

    public Integer agencyNo;

    public Integer recommenderNo;

    public String affiliateStatus;

    public String affiliateType;

    public String regType;

    public Integer regAdminNo;

    public String affiliateEmail;

    public String greenPointAccStatus;

    public String redPointAccStatus;

    public String greenPointUseStatus;

    public String redPointUseStatus;

    public Date createTime;

    public Date updateTime;

    public Integer getAffiliateNo() {
        return affiliateNo;
    }

    public void setAffiliateNo(Integer affiliateNo) {
        this.affiliateNo = affiliateNo;
    }

    public String getAffiliateSerial() {
        return affiliateSerial;
    }

    public void setAffiliateSerial(String affiliateSerial) {
        this.affiliateSerial = affiliateSerial == null ? null : affiliateSerial.trim();
    }

    public Float getAffiliateComm() {
        return affiliateComm;
    }

    public void setAffiliateComm(Float affiliateComm) {
        this.affiliateComm = affiliateComm;
    }

    public String getAffiliateCode() {
        return affiliateCode;
    }

    public void setAffiliateCode(String affiliateCode) {
        this.affiliateCode = affiliateCode == null ? null : affiliateCode.trim();
    }

    public String getAffiliateName() {
        return affiliateName;
    }

    public void setAffiliateName(String affiliateName) {
        this.affiliateName = affiliateName == null ? null : affiliateName.trim();
    }

    public String getAffiliateAddress() {
        return affiliateAddress;
    }

    public void setAffiliateAddress(String affiliateAddress) {
        this.affiliateAddress = affiliateAddress == null ? null : affiliateAddress.trim();
    }

    public String getAffiliateTel() {
        return affiliateTel;
    }

    public void setAffiliateTel(String affiliateTel) {
        this.affiliateTel = affiliateTel == null ? null : affiliateTel.trim();
    }

    public String getAffiliatePhone() {
        return affiliatePhone;
    }

    public void setAffiliatePhone(String affiliatePhone) {
        this.affiliatePhone = affiliatePhone == null ? null : affiliatePhone.trim();
    }

    public Integer getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Integer memberNo) {
        this.memberNo = memberNo;
    }

    public Integer getAgencyNo() {
        return agencyNo;
    }

    public void setAgencyNo(Integer agencyNo) {
        this.agencyNo = agencyNo;
    }

    public Integer getRecommenderNo() {
        return recommenderNo;
    }

    public void setRecommenderNo(Integer recommenderNo) {
        this.recommenderNo = recommenderNo;
    }

    public String getAffiliateStatus() {
        return affiliateStatus;
    }

    public void setAffiliateStatus(String affiliateStatus) {
        this.affiliateStatus = affiliateStatus == null ? null : affiliateStatus.trim();
    }

    public String getAffiliateType() {
        return affiliateType;
    }

    public void setAffiliateType(String affiliateType) {
        this.affiliateType = affiliateType == null ? null : affiliateType.trim();
    }

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType == null ? null : regType.trim();
    }

    public Integer getRegAdminNo() {
        return regAdminNo;
    }

    public void setRegAdminNo(Integer regAdminNo) {
        this.regAdminNo = regAdminNo;
    }

    public String getAffiliateEmail() {
        return affiliateEmail;
    }

    public void setAffiliateEmail(String affiliateEmail) {
        this.affiliateEmail = affiliateEmail == null ? null : affiliateEmail.trim();
    }

    public String getGreenPointAccStatus() {
        return greenPointAccStatus;
    }

    public void setGreenPointAccStatus(String greenPointAccStatus) {
        this.greenPointAccStatus = greenPointAccStatus == null ? null : greenPointAccStatus.trim();
    }

    public String getRedPointAccStatus() {
        return redPointAccStatus;
    }

    public void setRedPointAccStatus(String redPointAccStatus) {
        this.redPointAccStatus = redPointAccStatus == null ? null : redPointAccStatus.trim();
    }

    public String getGreenPointUseStatus() {
        return greenPointUseStatus;
    }

    public void setGreenPointUseStatus(String greenPointUseStatus) {
        this.greenPointUseStatus = greenPointUseStatus == null ? null : greenPointUseStatus.trim();
    }

    public String getRedPointUseStatus() {
        return redPointUseStatus;
    }

    public void setRedPointUseStatus(String redPointUseStatus) {
        this.redPointUseStatus = redPointUseStatus == null ? null : redPointUseStatus.trim();
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