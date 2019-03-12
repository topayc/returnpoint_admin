package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Product extends QueryCondition {

	private Integer productNo;
    private String productName;
    private Integer productPrice;
    private Integer productSalePrice;
    private String productDes;
    private String productStatus;
    private String productCategory;
    private String productImgPath1;
    private String productImgPath2;
    
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

    public Integer getProductNo() {
        return productNo;
    }

    public void setProductNo(Integer productNo) {
        this.productNo = productNo;
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

    public Integer getProductSalePrice() {
        return productSalePrice;
    }

    public void setProductSalePrice(Integer productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes == null ? null : productDes.trim();
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus == null ? null : productStatus.trim();
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory == null ? null : productCategory.trim();
    }

    public String getProductImgPath1() {
        return productImgPath1;
    }

    public void setProductImgPath1(String productImgPath1) {
        this.productImgPath1 = productImgPath1 == null ? null : productImgPath1.trim();
    }

    public String getProductImgPath2() {
        return productImgPath2;
    }

    public void setProductImgPath2(String productImgPath2) {
        this.productImgPath2 = productImgPath2 == null ? null : productImgPath2.trim();
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