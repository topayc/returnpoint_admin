package com.returnp.admin.dto.command;

import com.returnp.admin.model.PaymentTransaction;

public class PaymentTransactionCommand extends PaymentTransaction {
	public String affiliateName;
	public int  paymentTransactionRouterNo;
	public String paymentTransactionRouterType;
	public String paymentTransactionRouterName;

	public String getAffiliateName() {
		return affiliateName;
	}

	public void setAffiliateName(String affiliateName) {
		this.affiliateName = affiliateName;
	}

	public int getPaymentTransactionRouterNo() {
		return paymentTransactionRouterNo;
	}

	public void setPaymentTransactionRouterNo(int paymentTransactionRouterNo) {
		this.paymentTransactionRouterNo = paymentTransactionRouterNo;
	}

	public String getPaymentTransactionRouterType() {
		return paymentTransactionRouterType;
	}

	public void setPaymentTransactionRouterType(String paymentTransactionRouterType) {
		this.paymentTransactionRouterType = paymentTransactionRouterType;
	}

	public String getPaymentTransactionRouterName() {
		return paymentTransactionRouterName;
	}

	public void setPaymentTransactionRouterName(String paymentTransactionRouterName) {
		this.paymentTransactionRouterName = paymentTransactionRouterName;
	}
	
	
	
}
