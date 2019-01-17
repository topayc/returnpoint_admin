package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class PointWithdrawal extends QueryCondition {
    private Integer pointWithdrawalNo;

    private Integer memberNo;

    private Integer memberBankAccountNo;

    private Integer withdrawalAmount;

    private String withdrawalStatus;

    private String withdrawalPointType;

    private String regType;

    private Integer regAdminNo;

    private Date createTime;

    private Date updateTime;

    private String withdrawalMessage;

    public Integer getPointWithdrawalNo() {
        return pointWithdrawalNo;
    }

    public void setPointWithdrawalNo(Integer pointWithdrawalNo) {
        this.pointWithdrawalNo = pointWithdrawalNo;
    }

    public Integer getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Integer memberNo) {
        this.memberNo = memberNo;
    }

    public Integer getMemberBankAccountNo() {
        return memberBankAccountNo;
    }

    public void setMemberBankAccountNo(Integer memberBankAccountNo) {
        this.memberBankAccountNo = memberBankAccountNo;
    }

    public Integer getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(Integer withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public String getWithdrawalStatus() {
        return withdrawalStatus;
    }

    public void setWithdrawalStatus(String withdrawalStatus) {
        this.withdrawalStatus = withdrawalStatus == null ? null : withdrawalStatus.trim();
    }

    public String getWithdrawalPointType() {
        return withdrawalPointType;
    }

    public void setWithdrawalPointType(String withdrawalPointType) {
        this.withdrawalPointType = withdrawalPointType == null ? null : withdrawalPointType.trim();
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

    public String getWithdrawalMessage() {
        return withdrawalMessage;
    }

    public void setWithdrawalMessage(String withdrawalMessage) {
        this.withdrawalMessage = withdrawalMessage == null ? null : withdrawalMessage.trim();
    }
}