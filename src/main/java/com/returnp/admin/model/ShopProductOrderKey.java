package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;

public class ShopProductOrderKey extends QueryCondition {
    private Integer shopProductOrderNo;

    private String status;

    private String receiverPhone;

    public Integer getShopProductOrderNo() {
        return shopProductOrderNo;
    }

    public void setShopProductOrderNo(Integer shopProductOrderNo) {
        this.shopProductOrderNo = shopProductOrderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }
}