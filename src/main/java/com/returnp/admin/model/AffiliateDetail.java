package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class AffiliateDetail extends QueryCondition {
    private Integer affiliateDetailNo;

    private Integer affiliateNo;

    private String businessNumber;

    private String businessItem;

    private String buisnessName;

    private String businessType;

    private String overview;

    private String afffiliateNotice;

    private String holiday;

    private String openingHours;

    private String commonWeb;

    private String etcLink;

    private String affiliateNews;

    private String etc;

    private String affiliateMainImage1;

    private String affiliateMainImage2;

    private String affiliateMainImage3;

    private String affiliateMainImage4;

    private Date createTime;

    private Date updateTime;
    
    private MultipartFile amImage1;
    private MultipartFile amImage2;
    private MultipartFile amImage3;
    private MultipartFile amImage4;

    
    public MultipartFile getAmImage1() {
		return amImage1;
	}

	public void setAmImage1(MultipartFile amImage1) {
		this.amImage1 = amImage1;
	}

	public MultipartFile getAmImage2() {
		return amImage2;
	}

	public void setAmImage2(MultipartFile amImage2) {
		this.amImage2 = amImage2;
	}

	public MultipartFile getAmImage3() {
		return amImage3;
	}

	public void setAmImage3(MultipartFile amImage3) {
		this.amImage3 = amImage3;
	}

	public MultipartFile getAmImage4() {
		return amImage4;
	}

	public void setAmImage4(MultipartFile amImage4) {
		this.amImage4 = amImage4;
	}

	public Integer getAffiliateDetailNo() {
        return affiliateDetailNo;
    }

    public void setAffiliateDetailNo(Integer affiliateDetailNo) {
        this.affiliateDetailNo = affiliateDetailNo;
    }

    public Integer getAffiliateNo() {
        return affiliateNo;
    }

    public void setAffiliateNo(Integer affiliateNo) {
        this.affiliateNo = affiliateNo;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber == null ? null : businessNumber.trim();
    }

    public String getBusinessItem() {
        return businessItem;
    }

    public void setBusinessItem(String businessItem) {
        this.businessItem = businessItem == null ? null : businessItem.trim();
    }

    public String getBuisnessName() {
        return buisnessName;
    }

    public void setBuisnessName(String buisnessName) {
        this.buisnessName = buisnessName == null ? null : buisnessName.trim();
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview == null ? null : overview.trim();
    }

    public String getAfffiliateNotice() {
        return afffiliateNotice;
    }

    public void setAfffiliateNotice(String afffiliateNotice) {
        this.afffiliateNotice = afffiliateNotice == null ? null : afffiliateNotice.trim();
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday == null ? null : holiday.trim();
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours == null ? null : openingHours.trim();
    }

    public String getCommonWeb() {
        return commonWeb;
    }

    public void setCommonWeb(String commonWeb) {
        this.commonWeb = commonWeb == null ? null : commonWeb.trim();
    }

    public String getEtcLink() {
        return etcLink;
    }

    public void setEtcLink(String etcLink) {
        this.etcLink = etcLink == null ? null : etcLink.trim();
    }

    public String getAffiliateNews() {
        return affiliateNews;
    }

    public void setAffiliateNews(String affiliateNews) {
        this.affiliateNews = affiliateNews == null ? null : affiliateNews.trim();
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc == null ? null : etc.trim();
    }

    public String getAffiliateMainImage1() {
        return affiliateMainImage1;
    }

    public void setAffiliateMainImage1(String affiliateMainImage1) {
        this.affiliateMainImage1 = affiliateMainImage1 == null ? null : affiliateMainImage1.trim();
    }

    public String getAffiliateMainImage2() {
        return affiliateMainImage2;
    }

    public void setAffiliateMainImage2(String affiliateMainImage2) {
        this.affiliateMainImage2 = affiliateMainImage2 == null ? null : affiliateMainImage2.trim();
    }

    public String getAffiliateMainImage3() {
        return affiliateMainImage3;
    }

    public void setAffiliateMainImage3(String affiliateMainImage3) {
        this.affiliateMainImage3 = affiliateMainImage3 == null ? null : affiliateMainImage3.trim();
    }

    public String getAffiliateMainImage4() {
        return affiliateMainImage4;
    }

    public void setAffiliateMainImage4(String affiliateMainImage4) {
        this.affiliateMainImage4 = affiliateMainImage4 == null ? null : affiliateMainImage4.trim();
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