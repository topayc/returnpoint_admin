package com.returnp.admin.dto.command;

import com.returnp.admin.model.PaymentTransaction;

public class PaymentTransactionCommand extends PaymentTransaction {
	public String affiliateName;

	public String getAffiliateName() {
		return affiliateName;
	}

	public void setAffiliateName(String affiliateName) {
		this.affiliateName = affiliateName;
	}
	
}
