package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;

public class AdminRole extends QueryCondition implements GrantedAuthority {
    

	private static final long serialVersionUID = 1L;
	
	private String name;	
	
	private Integer adminRoleNo;

    private Integer adminNo;

    private String role;

    private Integer regAdminNo;

    private Date createTime;

    private Date updateTime;
    

	@Override
	public final String getAuthority() {
		// TODO Auto-generated method stub
		return this.role;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return role;
	}

	/**
	 * @param value the name to set
	 */
	public final void setName(final String value) {
		this.name = value;
	}

    public Integer getAdminRoleNo() {
        return adminRoleNo;
    }

    public void setAdminRoleNo(Integer adminRoleNo) {
        this.adminRoleNo = adminRoleNo;
    }

    public Integer getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(Integer adminNo) {
        this.adminNo = adminNo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
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