package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class NodeFileAttach extends QueryCondition {
    private Integer nodeFileAttachNo;

    private String fileName;

    private String originalFileName;

    private String fileType;

    private String fileSize;

    private String fileExt;

    private String regType;

    private Integer regAdminNo;

    private String fileLocalPath;

    private String fileWebPath;

    private Date createTime;

    private Date updateTime;

    public Integer getNodeFileAttachNo() {
        return nodeFileAttachNo;
    }

    public void setNodeFileAttachNo(Integer nodeFileAttachNo) {
        this.nodeFileAttachNo = nodeFileAttachNo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName == null ? null : originalFileName.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? null : fileSize.trim();
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt == null ? null : fileExt.trim();
    }

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType == null ? null : regType.trim();
    }

    public Integer getRegAdminNo() {
        return regAdminNo;
    }

    public void setRegAdminNo(Integer regAdminNo) {
        this.regAdminNo = regAdminNo;
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
}