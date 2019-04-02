package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;

public class GiftCardIssueKey extends QueryCondition {
    private Integer giftCardIssueNo;

    private String pinNumber;

    public Integer getGiftCardIssueNo() {
        return giftCardIssueNo;
    }

    public void setGiftCardIssueNo(Integer giftCardIssueNo) {
        this.giftCardIssueNo = giftCardIssueNo;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber == null ? null : pinNumber.trim();
    }
}