package com.returnp.admin.model;

import com.returnp.admin.dto.QueryCondition;
import java.util.Date;

public class MemberAddress extends QueryCondition {
    private Integer memberAddressNo;

    private Integer memberNo;

    private Integer nodeNo;

    private String nodeType;

    private String roadFullAddr;

    private String roadAddrPart1;

    private String roadAddrPart2;

    private String addrDetail;

    private String engAddr;

    private String jibunAddr;

    private String zipNo;

    private String admCd;

    private String rnMgtSn;

    private String bdMgtSn;

    private String lat;

    private String lng;

    private Integer regAdminNo;

    private Date createTime;

    private Date updateTime;

    public Integer getMemberAddressNo() {
        return memberAddressNo;
    }

    public void setMemberAddressNo(Integer memberAddressNo) {
        this.memberAddressNo = memberAddressNo;
    }

    public Integer getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Integer memberNo) {
        this.memberNo = memberNo;
    }

    public Integer getNodeNo() {
        return nodeNo;
    }

    public void setNodeNo(Integer nodeNo) {
        this.nodeNo = nodeNo;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType == null ? null : nodeType.trim();
    }

    public String getRoadFullAddr() {
        return roadFullAddr;
    }

    public void setRoadFullAddr(String roadFullAddr) {
        this.roadFullAddr = roadFullAddr == null ? null : roadFullAddr.trim();
    }

    public String getRoadAddrPart1() {
        return roadAddrPart1;
    }

    public void setRoadAddrPart1(String roadAddrPart1) {
        this.roadAddrPart1 = roadAddrPart1 == null ? null : roadAddrPart1.trim();
    }

    public String getRoadAddrPart2() {
        return roadAddrPart2;
    }

    public void setRoadAddrPart2(String roadAddrPart2) {
        this.roadAddrPart2 = roadAddrPart2 == null ? null : roadAddrPart2.trim();
    }

    public String getAddrDetail() {
        return addrDetail;
    }

    public void setAddrDetail(String addrDetail) {
        this.addrDetail = addrDetail == null ? null : addrDetail.trim();
    }

    public String getEngAddr() {
        return engAddr;
    }

    public void setEngAddr(String engAddr) {
        this.engAddr = engAddr == null ? null : engAddr.trim();
    }

    public String getJibunAddr() {
        return jibunAddr;
    }

    public void setJibunAddr(String jibunAddr) {
        this.jibunAddr = jibunAddr == null ? null : jibunAddr.trim();
    }

    public String getZipNo() {
        return zipNo;
    }

    public void setZipNo(String zipNo) {
        this.zipNo = zipNo == null ? null : zipNo.trim();
    }

    public String getAdmCd() {
        return admCd;
    }

    public void setAdmCd(String admCd) {
        this.admCd = admCd == null ? null : admCd.trim();
    }

    public String getRnMgtSn() {
        return rnMgtSn;
    }

    public void setRnMgtSn(String rnMgtSn) {
        this.rnMgtSn = rnMgtSn == null ? null : rnMgtSn.trim();
    }

    public String getBdMgtSn() {
        return bdMgtSn;
    }

    public void setBdMgtSn(String bdMgtSn) {
        this.bdMgtSn = bdMgtSn == null ? null : bdMgtSn.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
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