package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class AdminFile extends QueryCondition {
    private Integer adminFileNo;

    private String uploadType;

    private String fileNodeType;

    private String fileName;

    private String orginalFileName;

    private String fileLocalPath;

    private String fileWebPath;

    private Date createTime;

    private Date updateTime;

    private String regAdminEmail;

    public Integer getAdminFileNo() {
        return adminFileNo;
    }

    public void setAdminFileNo(Integer adminFileNo) {
        this.adminFileNo = adminFileNo;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType == null ? null : uploadType.trim();
    }

    public String getFileNodeType() {
        return fileNodeType;
    }

    public void setFileNodeType(String fileNodeType) {
        this.fileNodeType = fileNodeType == null ? null : fileNodeType.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getOrginalFileName() {
        return orginalFileName;
    }

    public void setOrginalFileName(String orginalFileName) {
        this.orginalFileName = orginalFileName == null ? null : orginalFileName.trim();
    }

    public String getFileLocalPath() {
        return fileLocalPath;
    }

    public void setFileLocalPath(String fileLocalPath) {
        this.fileLocalPath = fileLocalPath == null ? null : fileLocalPath.trim();
    }

    public String getFileWebPath() {
        return fileWebPath;
    }

    public void setFileWebPath(String fileWebPath) {
        this.fileWebPath = fileWebPath == null ? null : fileWebPath.trim();
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

    public String getRegAdminEmail() {
        return regAdminEmail;
    }

    public void setRegAdminEmail(String regAdminEmail) {
        this.regAdminEmail = regAdminEmail == null ? null : regAdminEmail.trim();
    }
}