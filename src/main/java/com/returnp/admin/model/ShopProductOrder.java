package com.returnp.admin.model;

import java.util.Date;

public class ShopProductOrder extends ShopProductOrderKey {
    private String orderNumber;

    private Integer orderMemberNo;

    private String productName;

    private Integer productPrice;

    private String orderColor;

    private Integer orderUnit;

    private Integer orderQty;

    private Integer totalPriceAmount;

    private Integer orderAmount;

    private String payType;

    private Float gpointRate;

    private Float gpointAmount;

    private String receiverName;

    private String receiverAddress;

    private String reqMsg;

    private String deliveryChargeType;

    private Integer deliveryCharge;

    private Date createTime;

    private Date updateTime;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public Integer getOrderMemberNo() {
        return orderMemberNo;
    }

    public void setOrderMemberNo(Integer orderMemberNo) {
        this.orderMemberNo = orderMemberNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getOrderColor() {
        return orderColor;
    }

    public void setOrderColor(String orderColor) {
        this.orderColor = orderColor == null ? null : orderColor.trim();
    }

    public Integer getOrderUnit() {
        return orderUnit;
    }

    public void setOrderUnit(Integer orderUnit) {
        this.orderUnit = orderUnit;
    }

    public Integer getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }

    public Integer getTotalPriceAmount() {
        return totalPriceAmount;
    }

    public void setTotalPriceAmount(Integer totalPriceAmount) {
        this.totalPriceAmount = totalPriceAmount;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Float getGpointRate() {
        return gpointRate;
    }

    public void setGpointRate(Float gpointRate) {
        this.gpointRate = gpointRate;
    }

    public Float getGpointAmount() {
        return gpointAmount;
    }

    public void setGpointAmount(Float gpointAmount) {
        this.gpointAmount = gpointAmount;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    public String getReqMsg() {
        return reqMsg;
    }

    public void setReqMsg(String reqMsg) {
        this.reqMsg = reqMsg == null ? null : reqMsg.trim();
    }

    public String getDeliveryChargeType() {
        return deliveryChargeType;
    }

    public void setDeliveryChargeType(String deliveryChargeType) {
        this.deliveryChargeType = deliveryChargeType == null ? null : deliveryChargeType.trim();
    }

    public Integer getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(Integer deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
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