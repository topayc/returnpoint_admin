package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class GiftCardIssue extends QueryCondition {
    private Integer giftCardIssueNo;

    private Integer giftCardOrderNo;

    private String giftCardNo;

    private String pinNumber;

    private String accableStatus;

    private String payableStatus;

    private String giftCardStaus;

    private String giftCardType;

    private Integer giftCardAmount;

    private Integer giftCardSalePrice;

    private String accQrData;

    private String payQrData;

    private String accQrScanner;

    private String payQrScanner;

    private Date accQrScanTime;

    private Date payQrScanTime;

    private Date createTime;

    private Date updateTime;

    public Integer getGiftCardIssueNo() {
        return giftCardIssueNo;
    }

    public void setGiftCardIssueNo(Integer giftCardIssueNo) {
        this.giftCardIssueNo = giftCardIssueNo;
    }

    public Integer getGiftCardOrderNo() {
        return giftCardOrderNo;
    }

    public void setGiftCardOrderNo(Integer giftCardOrderNo) {
        this.giftCardOrderNo = giftCardOrderNo;
    }

    public String getGiftCardNo() {
        return giftCardNo;
    }

    public void setGiftCardNo(String giftCardNo) {
        this.giftCardNo = giftCardNo == null ? null : giftCardNo.trim();
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber == null ? null : pinNumber.trim();
    }

    public String getAccableStatus() {
        return accableStatus;
    }

    public void setAccableStatus(String accableStatus) {
        this.accableStatus = accableStatus == null ? null : accableStatus.trim();
    }

    public String getPayableStatus() {
        return payableStatus;
    }

    public void setPayableStatus(String payableStatus) {
        this.payableStatus = payableStatus == null ? null : payableStatus.trim();
    }

    public String getGiftCardStaus() {
        return giftCardStaus;
    }

    public void setGiftCardStaus(String giftCardStaus) {
        this.giftCardStaus = giftCardStaus == null ? null : giftCardStaus.trim();
    }

    public String getGiftCardType() {
        return giftCardType;
    }

    public void setGiftCardType(String giftCardType) {
        this.giftCardType = giftCardType == null ? null : giftCardType.trim();
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
        this.accQrData = accQrData == null ? null : accQrData.trim();
    }

    public String getPayQrData() {
        return payQrData;
    }

    public void setPayQrData(String payQrData) {
        this.payQrData = payQrData == null ? null : payQrData.trim();
    }

    public String getAccQrScanner() {
        return accQrScanner;
    }

    public void setAccQrScanner(String accQrScanner) {
        this.accQrScanner = accQrScanner == null ? null : accQrScanner.trim();
    }

    public String getPayQrScanner() {
        return payQrScanner;
    }

    public void setPayQrScanner(String payQrScanner) {
        this.payQrScanner = payQrScanner == null ? null : payQrScanner.trim();
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