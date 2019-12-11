package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class PointCodeIssue extends QueryCondition {
    private Integer pointCodeIssueNo;

    private Integer pointCodeIssueRequestNo;

    private Integer memberNo;

    private String pointCode;

    private String pointBackStatus;

    private String useStatus;

    private Date createTime;

    private Date updateTime;

    public Integer getPointCodeIssueNo() {
        return pointCodeIssueNo;
    }

    public void setPointCodeIssueNo(Integer pointCodeIssueNo) {
        this.pointCodeIssueNo = pointCodeIssueNo;
    }

    public Integer getPointCodeIssueRequestNo() {
        return pointCodeIssueRequestNo;
    }

    public void setPointCodeIssueRequestNo(Integer pointCodeIssueRequestNo) {
        this.pointCodeIssueRequestNo = pointCodeIssueRequestNo;
    }

    public Integer getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Integer memberNo) {
        this.memberNo = memberNo;
    }

    public String getPointCode() {
        return pointCode;
    }

    public void setPointCode(String pointCode) {
        this.pointCode = pointCode == null ? null : pointCode.trim();
    }

    public String getPointBackStatus() {
        return pointBackStatus;
    }

    public void setPointBackStatus(String pointBackStatus) {
        this.pointBackStatus = pointBackStatus == null ? null : pointBackStatus.trim();
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus == null ? null : useStatus.trim();
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