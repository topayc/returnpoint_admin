package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class SubBbs extends QueryCondition {
    private Integer subBbsNo;

    private Integer mainBbsNo;

    private Integer writerNo;

    private Date createTime;

    private Date updateTime;

    private String content;

    public Integer getSubBbsNo() {
        return subBbsNo;
    }

    public void setSubBbsNo(Integer subBbsNo) {
        this.subBbsNo = subBbsNo;
    }

    public Integer getMainBbsNo() {
        return mainBbsNo;
    }

    public void setMainBbsNo(Integer mainBbsNo) {
        this.mainBbsNo = mainBbsNo;
    }

    public Integer getWriterNo() {
        return writerNo;
    }

    public void setWriterNo(Integer writerNo) {
        this.writerNo = writerNo;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}