package com.returnp.admin.dto.command;

import com.returnp.admin.model.GiftCardSalesOrgan;

public class GiftCardSalesOrganCommand extends GiftCardSalesOrgan {
	private String parentOrganName;

	public String getParentOrganName() {
		return parentOrganName;
	}

	public void setParentOrganName(String parentOrganName) {
		this.parentOrganName = parentOrganName;
	}
	
}
