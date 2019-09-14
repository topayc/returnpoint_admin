package com.returnp.admin.model;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.returnp.admin.dto.QueryCondition;

public class Admin extends QueryCondition implements UserDetails{
    
	 private List<AdminRole> authorities;
	private Integer adminNo;

    private String adminName;

    private String adminEmail;

    private String adminPassword;

    private String authType;

    private String level;

    private String levelCode;

    private String initAuthMenu;

    private Integer regAdminNo;

    private Date createTime;

    private Date updateTime;

    public Integer getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(Integer adminNo) {
        this.adminNo = adminNo;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail == null ? null : adminEmail.trim();
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType == null ? null : authType.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode == null ? null : levelCode.trim();
    }

    public String getInitAuthMenu() {
        return initAuthMenu;
    }

    public void setInitAuthMenu(String initAuthMenu) {
        this.initAuthMenu = initAuthMenu == null ? null : initAuthMenu.trim();
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
    
    @Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return adminPassword;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return adminEmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<AdminRole> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(List<AdminRole> authorities) {
		this.authorities = authorities;
	}
}