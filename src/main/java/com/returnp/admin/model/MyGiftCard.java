package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class MyGiftCard extends QueryCondition {
    private Integer myGiftCardNo;

    private Integer memberNo;

    private Integer giftCardIssueNo;

    private Date createTime;

    private Date updateTime;

    public Integer getMyGiftCardNo() {
        return myGiftCardNo;
    }

    public void setMyGiftCardNo(Integer myGiftCardNo) {
        this.myGiftCardNo = myGiftCardNo;
    }

    public Integer getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Integer memberNo) {
        this.memberNo = memberNo;
    }

    public Integer getGiftCardIssueNo() {
        return giftCardIssueNo;
    }

    public void setGiftCardIssueNo(Integer giftCardIssueNo) {
        this.giftCardIssueNo = giftCardIssueNo;
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