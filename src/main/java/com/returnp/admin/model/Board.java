package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class Board extends QueryCondition {
    private Integer boardNo;

    private String boardType;

    private String boardName;

    private String boardTitle;

    private Integer boardWriterNo;

    private String boardWriterName;

    private String boardWriterEmail;

    private String boardWriterType;

    private Integer viewHitCount;

    private String isPublic;

    private Integer boardPid;

    private Integer boardRef;

    private Integer boardLevel;

    private String boardCate;

    private String replyStatus;

    private String rerv1;

    private String rerv2;

    private String rerv3;

    private String rerv4;

    private String rerv5;

    private String rerv6;

    private String rerv7;

    private Date createTime;

    private Date updateTime;

    private String boardContent;

    public Integer getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(Integer boardNo) {
        this.boardNo = boardNo;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType == null ? null : boardType.trim();
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName == null ? null : boardName.trim();
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle == null ? null : boardTitle.trim();
    }

    public Integer getBoardWriterNo() {
        return boardWriterNo;
    }

    public void setBoardWriterNo(Integer boardWriterNo) {
        this.boardWriterNo = boardWriterNo;
    }

    public String getBoardWriterName() {
        return boardWriterName;
    }

    public void setBoardWriterName(String boardWriterName) {
        this.boardWriterName = boardWriterName == null ? null : boardWriterName.trim();
    }

    public String getBoardWriterEmail() {
        return boardWriterEmail;
    }

    public void setBoardWriterEmail(String boardWriterEmail) {
        this.boardWriterEmail = boardWriterEmail == null ? null : boardWriterEmail.trim();
    }

    public String getBoardWriterType() {
        return boardWriterType;
    }

    public void setBoardWriterType(String boardWriterType) {
        this.boardWriterType = boardWriterType == null ? null : boardWriterType.trim();
    }

    public Integer getViewHitCount() {
        return viewHitCount;
    }

    public void setViewHitCount(Integer viewHitCount) {
        this.viewHitCount = viewHitCount;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic == null ? null : isPublic.trim();
    }

    public Integer getBoardPid() {
        return boardPid;
    }

    public void setBoardPid(Integer boardPid) {
        this.boardPid = boardPid;
    }

    public Integer getBoardRef() {
        return boardRef;
    }

    public void setBoardRef(Integer boardRef) {
        this.boardRef = boardRef;
    }

    public Integer getBoardLevel() {
        return boardLevel;
    }

    public void setBoardLevel(Integer boardLevel) {
        this.boardLevel = boardLevel;
    }

    public String getBoardCate() {
        return boardCate;
    }

    public void setBoardCate(String boardCate) {
        this.boardCate = boardCate == null ? null : boardCate.trim();
    }

    public String getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(String replyStatus) {
        this.replyStatus = replyStatus == null ? null : replyStatus.trim();
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

    public String getRerv7() {
        return rerv7;
    }

    public void setRerv7(String rerv7) {
        this.rerv7 = rerv7 == null ? null : rerv7.trim();
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

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent == null ? null : boardContent.trim();
    }
}