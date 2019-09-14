package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class GiftCardSalesOrgan extends QueryCondition {
    private Integer giftCardSalesOrganNo;

    private Integer parentOrganNo;

    private String organCode;

    private String organPassword;

    private String organOwner;

    private String organName;

    private String organBusinessNumber;

    private Float saleOrganSaleFeeRate;

    private String organStatus;

    private String organPhone;

    private String organEmail;

    private String organTel;

    private String organAddr;

    private String organBankName;

    private String organBankAccount;

    private String organBankAccountOwner;

    private String authType;

    private String level;

    private String levelCode;

    private String initAuthMenu;

    private Date createTime;

    private Date updateTime;

    public Integer getGiftCardSalesOrganNo() {
        return giftCardSalesOrganNo;
    }

    public void setGiftCardSalesOrganNo(Integer giftCardSalesOrganNo) {
        this.giftCardSalesOrganNo = giftCardSalesOrganNo;
    }

    public Integer getParentOrganNo() {
        return parentOrganNo;
    }

    public void setParentOrganNo(Integer parentOrganNo) {
        this.parentOrganNo = parentOrganNo;
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

    public Float getSaleOrganSaleFeeRate() {
        return saleOrganSaleFeeRate;
    }

    public void setSaleOrganSaleFeeRate(Float saleOrganSaleFeeRate) {
        this.saleOrganSaleFeeRate = saleOrganSaleFeeRate;
    }

    public String getOrganStatus() {
        return organStatus;
    }

    public void setOrganStatus(String organStatus) {
        this.organStatus = organStatus == null ? null : organStatus.trim();
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

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType == null ? null : authType.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode == null ? null : levelCode.trim();
    }

    public String getInitAuthMenu() {
        return initAuthMenu;
    }

    public void setInitAuthMenu(String initAuthMenu) {
        this.initAuthMenu = initAuthMenu == null ? null : initAuthMenu.trim();
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