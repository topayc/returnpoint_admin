package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class MemberLoginLog extends QueryCondition {
    private Integer memberLoginLogNo;

    private Integer memberNo;

    private String memberEmail;

    private Integer loginTryCount;

    private String loginResut;

    private Date createTime;

    private Date updateTime;

    public Integer getMemberLoginLogNo() {
        return memberLoginLogNo;
    }

    public void setMemberLoginLogNo(Integer memberLoginLogNo) {
        this.memberLoginLogNo = memberLoginLogNo;
    }

    public Integer getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Integer memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail == null ? null : memberEmail.trim();
    }

    public Integer getLoginTryCount() {
        return loginTryCount;
    }

    public void setLoginTryCount(Integer loginTryCount) {
        this.loginTryCount = loginTryCount;
    }

    public String getLoginResut() {
        return loginResut;
    }

    public void setLoginResut(String loginResut) {
        this.loginResut = loginResut == null ? null : loginResut.trim();
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