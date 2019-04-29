package com.returnp.admin.dto;

import java.util.Date;

import com.returnp.admin.model.Admin;
import com.returnp.admin.model.GiftCardSalesOrgan;

public class AdminSession {
	public Admin admin;
	public GiftCardSalesOrgan saleOrgan;
	public Date loginDatetime;
	public boolean isLogin;
	public String loginUserType;
	
	
	public String getLoginUserType() {
		return loginUserType;
	}
	public void setLoginUserType(String loginUserType) {
		this.loginUserType = loginUserType;
	}
	public GiftCardSalesOrgan getSaleOrgan() {
		return saleOrgan;
	}
	public void setSaleOrgan(GiftCardSalesOrgan saleOrgan) {
		this.saleOrgan = saleOrgan;
	}
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
