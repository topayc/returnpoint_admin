package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class GiftCardOrder extends QueryCondition {
    private Integer orderNo;

    private String orderNumber;

    private String orderName;

    private String ordererId;

    private String ordererName;

    private String ordererPhone;

    private String ordererEmail;

    private Integer orderTotalPrice;

    private String orderType;

    private String orderStatus;

    private String issueStatus;

    private String bargainType;

    private String orderReason;

    private String paymentStatus;

    private String paymentType;

    private String deliverNumber;

    private String deliveryAddress;

    private String deliveryMessage;

    private Date orderTime;

    private Date createTime;

    private Date updateTime;

    public Integer getOrderNo() {
        return orderNo;
    }

  

    public String getOrderNumber() {
		return orderNumber;
	}



	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}



	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}



	

    public String getOrderName() {
		return orderName;
	}



	public void setOrderName(String orderName) {
        this.orderName = orderName == null ? null : orderName.trim();
    }

    public String getOrdererId() {
        return ordererId;
    }

    public void setOrdererId(String ordererId) {
        this.ordererId = ordererId == null ? null : ordererId.trim();
    }

    public String getOrdererName() {
        return ordererName;
    }

    public void setOrdererName(String ordererName) {
        this.ordererName = ordererName == null ? null : ordererName.trim();
    }

    public String getOrdererPhone() {
        return ordererPhone;
    }

    public void setOrdererPhone(String ordererPhone) {
        this.ordererPhone = ordererPhone == null ? null : ordererPhone.trim();
    }

    public String getOrdererEmail() {
        return ordererEmail;
    }

    public void setOrdererEmail(String ordererEmail) {
        this.ordererEmail = ordererEmail == null ? null : ordererEmail.trim();
    }

    public Integer getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(Integer orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus == null ? null : issueStatus.trim();
    }

    public String getBargainType() {
        return bargainType;
    }

    public void setBargainType(String bargainType) {
        this.bargainType = bargainType == null ? null : bargainType.trim();
    }

    public String getOrderReason() {
        return orderReason;
    }

    public void setOrderReason(String orderReason) {
        this.orderReason = orderReason == null ? null : orderReason.trim();
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus == null ? null : paymentStatus.trim();
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    public String getDeliverNumber() {
        return deliverNumber;
    }

    public void setDeliverNumber(String deliverNumber) {
        this.deliverNumber = deliverNumber == null ? null : deliverNumber.trim();
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress == null ? null : deliveryAddress.trim();
    }

    public String getDeliveryMessage() {
        return deliveryMessage;
    }

    public void setDeliveryMessage(String deliveryMessage) {
        this.deliveryMessage = deliveryMessage == null ? null : deliveryMessage.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}