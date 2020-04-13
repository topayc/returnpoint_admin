package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class MemberUniqToken extends QueryCondition {
    private Integer memberUniqTokenNo;

    private Integer memberNo;

    private String token;

    private String memberName;

    private String memberEmail;

    private String memberPhone;

    private String tokenType;

    private Date createTime;

    private Date updatetiem;

    public Integer getMemberUniqTokenNo() {
        return memberUniqTokenNo;
    }

    public void setMemberUniqTokenNo(Integer memberUniqTokenNo) {
        this.memberUniqTokenNo = memberUniqTokenNo;
    }

    public Integer getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Integer memberNo) {
        this.memberNo = memberNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail == null ? null : memberEmail.trim();
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone == null ? null : memberPhone.trim();
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType == null ? null : tokenType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdatetiem() {
        return updatetiem;
    }

    public void setUpdatetiem(Date updatetiem) {
        this.updatetiem = updatetiem;
    }
}