package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class MainBbs extends QueryCondition {
    private Integer mainBbsNo;

    private String bbsType1;

    private String bbsType2;

    private Integer writerNo;

    private String title;

    private String isPublic;

    private String bbsPassword;

    private String replyCompleted;

    private Integer viewCount;

    private Date createTime;

    private Date updateTime;

    private String rerv1;

    private String rerv2;

    private String rerv3;

    private String rerv4;

    private String rerv5;

    private String rerv6;

    private String content;

    public Integer getMainBbsNo() {
        return mainBbsNo;
    }

    public void setMainBbsNo(Integer mainBbsNo) {
        this.mainBbsNo = mainBbsNo;
    }

    public String getBbsType1() {
        return bbsType1;
    }

    public void setBbsType1(String bbsType1) {
        this.bbsType1 = bbsType1 == null ? null : bbsType1.trim();
    }

    public String getBbsType2() {
        return bbsType2;
    }

    public void setBbsType2(String bbsType2) {
        this.bbsType2 = bbsType2 == null ? null : bbsType2.trim();
    }

    public Integer getWriterNo() {
        return writerNo;
    }

    public void setWriterNo(Integer writerNo) {
        this.writerNo = writerNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic == null ? null : isPublic.trim();
    }

    public String getBbsPassword() {
        return bbsPassword;
    }

    public void setBbsPassword(String bbsPassword) {
        this.bbsPassword = bbsPassword == null ? null : bbsPassword.trim();
    }

    public String getReplyCompleted() {
        return replyCompleted;
    }

    public void setReplyCompleted(String replyCompleted) {
        this.replyCompleted = replyCompleted == null ? null : replyCompleted.trim();
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
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

    public String getRerv1() {
        return rerv1;
    }

    public void setRerv1(String rerv1) {
        this.rerv1 = rerv1 == null ? null : rerv1.trim();
    }

    public String getRerv2() {
        return rerv2;
    }

    public void setRerv2(String rerv2) {
        this.rerv2 = rerv2 == null ? null : rerv2.trim();
    }

    public String getRerv3() {
        return rerv3;
    }

    public void setRerv3(String rerv3) {
        this.rerv3 = rerv3 == null ? null : rerv3.trim();
    }

    public String getRerv4() {
        return rerv4;
    }

    public void setRerv4(String rerv4) {
        this.rerv4 = rerv4 == null ? null : rerv4.trim();
    }

    public String getRerv5() {
        return rerv5;
    }

    public void setRerv5(String rerv5) {
        this.rerv5 = rerv5 == null ? null : rerv5.trim();
    }

    public String getRerv6() {
        return rerv6;
    }

    public void setRerv6(String rerv6) {
        this.rerv6 = rerv6 == null ? null : rerv6.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}