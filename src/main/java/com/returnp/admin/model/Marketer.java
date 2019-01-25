package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class Marketer extends QueryCondition {
    private Integer marketerNo;

    private Integer marketerDegree;

    private String marketerCode;

    private Integer memberNo;

    private String parent;

    private String marketerStatus;

    private Integer regAdminNo;

    private Date createTime;

    private Date updateTime;

    public Integer getMarketerNo() {
        return marketerNo;
    }

    public void setMarketerNo(Integer marketerNo) {
        this.marketerNo = marketerNo;
    }

    public Integer getMarketerDegree() {
        return marketerDegree;
    }

    public void setMarketerDegree(Integer marketerDegree) {
        this.marketerDegree = marketerDegree;
    }

    public String getMarketerCode() {
        return marketerCode;
    }

    public void setMarketerCode(String marketerCode) {
        this.marketerCode = marketerCode == null ? null : marketerCode.trim();
    }

    public Integer getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Integer memberNo) {
        this.memberNo = memberNo;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent == null ? null : parent.trim();
    }

    public String getMarketerStatus() {
        return marketerStatus;
    }

    public void setMarketerStatus(String marketerStatus) {
        this.marketerStatus = marketerStatus == null ? null : marketerStatus.trim();
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
}