package com.returnp.admin.dto;

import java.util.Date;

import com.returnp.admin.model.Admin;
import com.returnp.admin.model.GiftCardSalesOrgan;

public class AdminSession {
	public Admin admin;
	public GiftCardSalesOrgan saleOrgan;
	public Date loginDatetime;
	public boolean isLogin;
	public String adminType;
	public String getLoginName() {
		if (admin != null) {
			return admin.getAdminName();
		}else {
			return saleOrgan.getOrganName();
		}
	}
	
	
	public String getAdminTypeStr() {
		String str = null;
		switch(this.getAdminType()) {
		case "1":
			str = "시스템 관리자";
			break;
		case "10":
			str = "상품권 본사";
			break;
		case "11":
			str = "상품권 총판";
			break;
		case "12":
			str = "상품권 판매점";
			break;
		}
		return str;
		
	}
	
	public String getAdminType() {
		return adminType;
	}
	public void setAdminType(String adminType) {
		this.adminType = adminType;
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
