package com.returnp.admin.dto.command;

import com.returnp.admin.model.GiftCardOrderItem;

public class GiftCardOrderItemCommand extends GiftCardOrderItem {
	
	private String productName;
	private Integer productPrice;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
}
