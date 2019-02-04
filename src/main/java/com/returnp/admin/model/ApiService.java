package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class ApiService extends QueryCondition {
    private Integer apiServiceNo;

    private String apiService;

    private String apiServiceName;

    private String tId;

    private String company;

    private String project;

    private String domain;

    private String ip;

    private String apiKey;

    private String apiServiceStatus;

    private Date expireTime;

    private Integer regAdminNo;

    private Date createTime;

    private Date updateTime;

    public Integer getApiServiceNo() {
        return apiServiceNo;
    }

    public void setApiServiceNo(Integer apiServiceNo) {
        this.apiServiceNo = apiServiceNo;
    }

    public String getApiService() {
        return apiService;
    }

    public void setApiService(String apiService) {
        this.apiService = apiService == null ? null : apiService.trim();
    }

    public String getApiServiceName() {
        return apiServiceName;
    }

    public void setApiServiceName(String apiServiceName) {
        this.apiServiceName = apiServiceName == null ? null : apiServiceName.trim();
    }


    public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}

	public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project == null ? null : project.trim();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey == null ? null : apiKey.trim();
    }

    public String getApiServiceStatus() {
        return apiServiceStatus;
    }

    public void setApiServiceStatus(String apiServiceStatus) {
        this.apiServiceStatus = apiServiceStatus == null ? null : apiServiceStatus.trim();
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getRegAdminNo() {
        return regAdminNo;
    }

    public void setRegAdminNo(Integer regAdminNo) {
        this.regAdminNo = regAdminNo;
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