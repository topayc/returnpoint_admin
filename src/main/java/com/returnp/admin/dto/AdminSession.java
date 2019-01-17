package com.returnp.admin.dto;

import java.util.Date;

import com.returnp.admin.model.Admin;

public class AdminSession {
	public Admin admin;
	public Date loginDatetime;
	public boolean isLogin;
	
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Date getLoginDatetime() {
		return loginDatetime;
	}
	public void setLoginDatetime(Date loginDatetime) {
		this.loginDatetime = loginDatetime;
	}
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	

}
