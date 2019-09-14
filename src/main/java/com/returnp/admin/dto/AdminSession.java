package com.returnp.admin.dto;

import java.util.Date;

import com.returnp.admin.model.Admin;
import com.returnp.admin.model.GiftCardSalesOrgan;

public class AdminSession {
	public Admin admin;
	public GiftCardSalesOrgan saleOrgan;
	public Date loginDatetime;
	public boolean isLogin;
	public String authType;
	public String initAuthMenu;
	public String getAdminName() {
		if (admin != null) {
			return admin.getAdminName();
		}else {
			return saleOrgan.getOrganName();
		}
	}
	
	
	public String getInitAuthMenu() {
		if (admin != null) {
			return admin.getInitAuthMenu();
		}else {
			return saleOrgan.getInitAuthMenu();
		}
	}


	public void setInitAuthMenu(String initAuthMenu) {
		this.initAuthMenu = initAuthMenu;
	}


	public String getAuthTypeStr() {
		String str = null;
		switch(this.getAuthType()) {
		case "1":
			str = "탑해피 월드 시스템";
			break;
		case "10":
			str = "상품권 본사 시스템";
			break;
		case "11":
			str = "상품권 총판 시스템";
			break;
		case "12":
			str = "상품권 판매점 시스템";
			break;
		}
		return str;
		
	}


	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


	public GiftCardSalesOrgan getSaleOrgan() {
		return saleOrgan;
	}


	public void setSaleOrgan(GiftCardSalesOrgan saleOrgan) {
		this.saleOrgan = saleOrgan;
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


	public String getAuthType() {
		return authType;
	}


	public void setAuthType(String authType) {
		this.authType = authType;
	}
	
	
	

}
