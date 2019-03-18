package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class GiftCardSalesOrgan extends QueryCondition {
    private Integer giftCardSalesOrganNo;

    private String organCode;

    private String organPassword;

    private String organOwner;

    private String organName;

    private String organBusinessNumber;

    private String organStatus;

    private String organType;

    private String organPhone;

    private String organEmail;

    private String organTel;

    private String organAddr;

    private String organBankName;

    private String organBankAccount;

    private String organBankAccountOwner;

    private Date createTime;

    private Date updateTime;

    public Integer getGiftCardSalesOrganNo() {
        return giftCardSalesOrganNo;
    }

    public void setGiftCardSalesOrganNo(Integer giftCardSalesOrganNo) {
        this.giftCardSalesOrganNo = giftCardSalesOrganNo;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode == null ? null : organCode.trim();
    }

    public String getOrganPassword() {
        return organPassword;
    }

    public void setOrganPassword(String organPassword) {
        this.organPassword = organPassword == null ? null : organPassword.trim();
    }

    public String getOrganOwner() {
        return organOwner;
    }

    public void setOrganOwner(String organOwner) {
        this.organOwner = organOwner == null ? null : organOwner.trim();
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName == null ? null : organName.trim();
    }

    public String getOrganBusinessNumber() {
        return organBusinessNumber;
    }

    public void setOrganBusinessNumber(String organBusinessNumber) {
        this.organBusinessNumber = organBusinessNumber == null ? null : organBusinessNumber.trim();
    }

    public String getOrganStatus() {
        return organStatus;
    }

    public void setOrganStatus(String organStatus) {
        this.organStatus = organStatus == null ? null : organStatus.trim();
    }

    public String getOrganType() {
        return organType;
    }

    public void setOrganType(String organType) {
        this.organType = organType == null ? null : organType.trim();
    }

    public String getOrganPhone() {
        return organPhone;
    }

    public void setOrganPhone(String organPhone) {
        this.organPhone = organPhone == null ? null : organPhone.trim();
    }

    public String getOrganEmail() {
        return organEmail;
    }

    public void setOrganEmail(String organEmail) {
        this.organEmail = organEmail == null ? null : organEmail.trim();
    }

    public String getOrganTel() {
        return organTel;
    }

    public void setOrganTel(String organTel) {
        this.organTel = organTel == null ? null : organTel.trim();
    }

    public String getOrganAddr() {
        return organAddr;
    }

    public void setOrganAddr(String organAddr) {
        this.organAddr = organAddr == null ? null : organAddr.trim();
    }

    public String getOrganBankName() {
        return organBankName;
    }

    public void setOrganBankName(String organBankName) {
        this.organBankName = organBankName == null ? null : organBankName.trim();
    }

    public String getOrganBankAccount() {
        return organBankAccount;
    }

    public void setOrganBankAccount(String organBankAccount) {
        this.organBankAccount = organBankAccount == null ? null : organBankAccount.trim();
    }

    public String getOrganBankAccountOwner() {
        return organBankAccountOwner;
    }

    public void setOrganBankAccountOwner(String organBankAccountOwner) {
        this.organBankAccountOwner = organBankAccountOwner == null ? null : organBankAccountOwner.trim();
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