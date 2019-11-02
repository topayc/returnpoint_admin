package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class PointCouponTransaction extends QueryCondition {
    private Integer pointCouponTransactionNo;

    private Integer memberNo;

    private String pointCouponNo;

    private String pointBackStatus;

    private Date createTime;

    private Date updateTime;

    public Integer getPointCouponTransactionNo() {
        return pointCouponTransactionNo;
    }

    public void setPointCouponTransactionNo(Integer pointCouponTransactionNo) {
        this.pointCouponTransactionNo = pointCouponTransactionNo;
    }

    public Integer getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Integer memberNo) {
        this.memberNo = memberNo;
    }

    public String getPointCouponNo() {
        return pointCouponNo;
    }

    public void setPointCouponNo(String pointCouponNo) {
        this.pointCouponNo = pointCouponNo == null ? null : pointCouponNo.trim();
    }

    public String getPointBackStatus() {
        return pointBackStatus;
    }

    public void setPointBackStatus(String pointBackStatus) {
        this.pointBackStatus = pointBackStatus == null ? null : pointBackStatus.trim();
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