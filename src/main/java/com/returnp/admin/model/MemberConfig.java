package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class MemberConfig extends QueryCondition {
    private Integer memberConfigNo;

    private Integer memberNo;

    private String devicePush;

    private String emailReceive;

    private Date createTime;

    private Date updateTime;

    public Integer getMemberConfigNo() {
        return memberConfigNo;
    }

    public void setMemberConfigNo(Integer memberConfigNo) {
        this.memberConfigNo = memberConfigNo;
    }

    public Integer getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Integer memberNo) {
        this.memberNo = memberNo;
    }

    public String getDevicePush() {
        return devicePush;
    }

    public void setDevicePush(String devicePush) {
        this.devicePush = devicePush == null ? null : devicePush.trim();
    }

    public String getEmailReceive() {
        return emailReceive;
    }

    public void setEmailReceive(String emailReceive) {
        this.emailReceive = emailReceive == null ? null : emailReceive.trim();
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