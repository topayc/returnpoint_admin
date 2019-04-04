package com.returnp.admin.dto.command;

import com.returnp.admin.model.GiftCardPayment;

public class GiftCardPaymentCommand extends GiftCardPayment {
	private String affiliateName;
	private String affiliateEmail;
	private String affiliatePhone;
	private Integer giftCardNo;
	private String giftCardName;
	private Integer giftCardAmount;
	private String pinNumber;
	private String bankName;
	private String backAccount;
	private String accountOwner;
	public String getAffiliateName() {
		return affiliateName;
	}
	public void setAffiliateName(String affiliateName) {
		this.affiliateName = affiliateName;
	}
	public String getAffiliateEmail() {
		return affiliateEmail;
	}
	public void setAffiliateEmail(String affiliateEmail) {
		this.affiliateEmail = affiliateEmail;
	}
	public String getAffiliatePhone() {
		return affiliatePhone;
	}
	public void setAffiliatePhone(String affiliatePhone) {
		this.affiliatePhone = affiliatePhone;
	}
	public Integer getGiftCardNo() {
		return giftCardNo;
	}
	public void setGiftCardNo(Integer giftCardNo) {
		this.giftCardNo = giftCardNo;
	}
	public String getGiftCardName() {
		return giftCardName;
	}
	public void setGiftCardName(String giftCardName) {
		this.giftCardName = giftCardName;
	}
	public Integer getGiftCardAmount() {
		return giftCardAmount;
	}
	public void setGiftCardAmount(Integer giftCardAmount) {
		this.giftCardAmount = giftCardAmount;
	}
	public String getPinNumber() {
		return pinNumber;
	}
	public void setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBackAccount() {
		return backAccount;
	}
	public void setBackAccount(String backAccount) {
		this.backAccount = backAccount;
	}
	public String getAccountOwner() {
		return accountOwner;
	}
	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}
	
}
