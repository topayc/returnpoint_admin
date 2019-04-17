package com.returnp.admin.dto.command;

import java.util.Date;

import com.returnp.admin.model.MyGiftCard;

public class MyGiftCardCommand extends MyGiftCard {
	public String memberName;
	private Integer giftCardOrderNo;

    private Integer giftCardNo;

    private String accableStatus;

    private String payableStatus;

    private String giftCardStatus;

    private String giftCardType;

    private Integer giftCardAmount;

    private Integer giftCardSalePrice;

    private String accQrData;

    private String payQrData;

    private String accQrScanner;

    private String payQrScanner;

    private Date accQrScanTime;

    private Date payQrScanTime;

    private Date issueTime;

    private Date expirationTime;

    private String accQrCodeWebPath;

    private String payQrCodeWebPath;

    private String accQrCodeFilePath;

    private String payQrCodeFilePath;

    private String receiverPhone;

    private String receiverEmail;

    private String receiverName;

    private String receiverAddress;

    private String receiverIsMember;

    private String deliveryMessage;

    private String deliveryStatus;

    private String deliveryNumber;

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Integer getGiftCardOrderNo() {
		return giftCardOrderNo;
	}

	public void setGiftCardOrderNo(Integer giftCardOrderNo) {
		this.giftCardOrderNo = giftCardOrderNo;
	}

	public Integer getGiftCardNo() {
		return giftCardNo;
	}

	public void setGiftCardNo(Integer giftCardNo) {
		this.giftCardNo = giftCardNo;
	}

	public String getAccableStatus() {
		return accableStatus;
	}

	public void setAccableStatus(String accableStatus) {
		this.accableStatus = accableStatus;
	}

	public String getPayableStatus() {
		return payableStatus;
	}

	public void setPayableStatus(String payableStatus) {
		this.payableStatus = payableStatus;
	}

	public String getGiftCardStatus() {
		return giftCardStatus;
	}

	public void setGiftCardStatus(String giftCardStatus) {
		this.giftCardStatus = giftCardStatus;
	}

	public String getGiftCardType() {
		return giftCardType;
	}

	public void setGiftCardType(String giftCardType) {
		this.giftCardType = giftCardType;
	}

	public Integer getGiftCardAmount() {
		return giftCardAmount;
	}

	public void setGiftCardAmount(Integer giftCardAmount) {
		this.giftCardAmount = giftCardAmount;
	}

	public Integer getGiftCardSalePrice() {
		return giftCardSalePrice;
	}

	public void setGiftCardSalePrice(Integer giftCardSalePrice) {
		this.giftCardSalePrice = giftCardSalePrice;
	}

	public String getAccQrData() {
		return accQrData;
	}

	public void setAccQrData(String accQrData) {
		this.accQrData = accQrData;
	}

	public String getPayQrData() {
		return payQrData;
	}

	public void setPayQrData(String payQrData) {
		this.payQrData = payQrData;
	}

	public String getAccQrScanner() {
		return accQrScanner;
	}

	public void setAccQrScanner(String accQrScanner) {
		this.accQrScanner = accQrScanner;
	}

	public String getPayQrScanner() {
		return payQrScanner;
	}

	public void setPayQrScanner(String payQrScanner) {
		this.payQrScanner = payQrScanner;
	}

	public Date getAccQrScanTime() {
		return accQrScanTime;
	}

	public void setAccQrScanTime(Date accQrScanTime) {
		this.accQrScanTime = accQrScanTime;
	}

	public Date getPayQrScanTime() {
		return payQrScanTime;
	}

	public void setPayQrScanTime(Date payQrScanTime) {
		this.payQrScanTime = payQrScanTime;
	}

	public Date getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getAccQrCodeWebPath() {
		return accQrCodeWebPath;
	}

	public void setAccQrCodeWebPath(String accQrCodeWebPath) {
		this.accQrCodeWebPath = accQrCodeWebPath;
	}

	public String getPayQrCodeWebPath() {
		return payQrCodeWebPath;
	}

	public void setPayQrCodeWebPath(String payQrCodeWebPath) {
		this.payQrCodeWebPath = payQrCodeWebPath;
	}

	public String getAccQrCodeFilePath() {
		return accQrCodeFilePath;
	}

	public void setAccQrCodeFilePath(String accQrCodeFilePath) {
		this.accQrCodeFilePath = accQrCodeFilePath;
	}

	public String getPayQrCodeFilePath() {
		return payQrCodeFilePath;
	}

	public void setPayQrCodeFilePath(String payQrCodeFilePath) {
		this.payQrCodeFilePath = payQrCodeFilePath;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getReceiverEmail() {
		return receiverEmail;
	}

	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverIsMember() {
		return receiverIsMember;
	}

	public void setReceiverIsMember(String receiverIsMember) {
		this.receiverIsMember = receiverIsMember;
	}

	public String getDeliveryMessage() {
		return deliveryMessage;
	}

	public void setDeliveryMessage(String deliveryMessage) {
		this.deliveryMessage = deliveryMessage;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getDeliveryNumber() {
		return deliveryNumber;
	}

	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}
}
