package com.returnp.admin.dto.command;

import com.returnp.admin.model.GiftCardIssue;

public class GiftCardIssueCommand extends GiftCardIssue {
	private String orderName;
	private String orderNumber;
	private String ordererName;
	private String ordererPhone;
	private String giftCardName;
	private String ordererEmail;
	
	
	public String getOrdererEmail() {
		return ordererEmail;
	}
	public void setOrdererEmail(String ordererEmail) {
		this.ordererEmail = ordererEmail;
	}
	public String getOrderName() {
		return this.orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getOrderNumber() {
		return this.orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrdererName() {
		return this.ordererName;
	}
	public void setOrdererName(String ordererName) {
		this.ordererName = ordererName;
	}
	public String getOrdererPhone() {
		return this.ordererPhone;
	}
	public void setOrdererPhone(String ordererPhone) {
		this.ordererPhone = ordererPhone;
	}
	public String getGiftCardName() {
		return this.giftCardName;
	}
	public void setGiftCardName(String giftCardName) {
		this.giftCardName = giftCardName;
	}
	
}
