package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class GiftCard extends QueryCondition {
    private Integer giftCardNo;

    private String giftCardName;

    private Integer stockCount;

    private Integer giftCardAmount;

    private Integer giftCardSalePrice;

    private String giftCardDes;

    private String giftCardSaleStatus;

    private String giftCardCategory;

    private String giftCardImgPath1;

    private String giftCardImgPath2;
    
    private MultipartFile productImg1;
    private MultipartFile productImg2;
    
    

    public MultipartFile getProductImg1() {
		return productImg1;
	}

	public void setProductImg1(MultipartFile productImg1) {
		this.productImg1 = productImg1;
	}

	public MultipartFile getProductImg2() {
		return productImg2;
	}

	public void setProductImg2(MultipartFile productImg2) {
		this.productImg2 = productImg2;
	}

	private Date createTime;

    private Date updateTime;

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
        this.giftCardName = giftCardName == null ? null : giftCardName.trim();
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
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

    public String getGiftCardDes() {
        return giftCardDes;
    }

    public void setGiftCardDes(String giftCardDes) {
        this.giftCardDes = giftCardDes == null ? null : giftCardDes.trim();
    }

    public String getGiftCardSaleStatus() {
        return giftCardSaleStatus;
    }

    public void setGiftCardSaleStatus(String giftCardSaleStatus) {
        this.giftCardSaleStatus = giftCardSaleStatus == null ? null : giftCardSaleStatus.trim();
    }

    public String getGiftCardCategory() {
        return giftCardCategory;
    }

    public void setGiftCardCategory(String giftCardCategory) {
        this.giftCardCategory = giftCardCategory == null ? null : giftCardCategory.trim();
    }

    public String getGiftCardImgPath1() {
        return giftCardImgPath1;
    }

    public void setGiftCardImgPath1(String giftCardImgPath1) {
        this.giftCardImgPath1 = giftCardImgPath1 == null ? null : giftCardImgPath1.trim();
    }

    public String getGiftCardImgPath2() {
        return giftCardImgPath2;
    }

    public void setGiftCardImgPath2(String giftCardImgPath2) {
        this.giftCardImgPath2 = giftCardImgPath2 == null ? null : giftCardImgPath2.trim();
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