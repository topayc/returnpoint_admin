package com.returnp.admin.dto.request;

import com.returnp.admin.dto.QueryCondition;

public class SearchCondition extends QueryCondition {
	
	public int nodeNo;
	
	public String searchNodeType;
	public String searchDateStart;
	public String searchDateEnd;
	public String searchNodeStatus;
	public String searchKeywordType;
	public String searchKeyword;
	public String searchNodeAction;
	public String searchCallback;
	public String searchVanPaymentStatus;
	public String searchPaymentStatus;
	public String searchPaymentType;
	
	public String memberName;
	public String memberEmail;
	
	/* MembershipRequest 관련*/
	private Integer membershipRequestNo;
    private Integer memberNo;
    private Integer paymentAmount;
    private String paymentStatus;
    private String gradeType;
    private String paymentType;
    private String regType;
    private Integer regAdminNo;
  
    private String paymentTransactionType;
    private String paymentApprovalStatus;
    private String memberPhone;
    private String affiliateSerial;
    private String searchConversionStatus;
    
    private String searchApiServiceType;
    private String searchApiServiceStatus;
	
	public String getSearchConversionStatus() {
		return searchConversionStatus;
	}
	public void setSearchConversionStatus(String searchConversionStatus) {
		this.searchConversionStatus = searchConversionStatus;
	}	
	public int getNodeNo() {
		return nodeNo;
	}
	public void setNodeNo(int nodeNo) {
		this.nodeNo = nodeNo;
	}
	public String getSearchNodeType() {
		return searchNodeType;
	}
	public void setSearchNodeType(String searchNodeType) {
		this.searchNodeType = searchNodeType;
	}
	public String getSearchDateStart() {
		return searchDateStart;
	}
	public void setSearchDateStart(String searchDateStart) {
		this.searchDateStart = searchDateStart;
	}
	public String getSearchDateEnd() {
		return searchDateEnd;
	}
	public void setSearchDateEnd(String searchDateEnd) {
		this.searchDateEnd = searchDateEnd;
	}
	public String getSearchNodeStatus() {
		return searchNodeStatus;
	}
	public void setSearchNodeStatus(String searchNodeStatus) {
		this.searchNodeStatus = searchNodeStatus;
	}
	public String getSearchKeywordType() {
		return searchKeywordType;
	}
	public void setSearchKeywordType(String searchKeywordType) {
		this.searchKeywordType = searchKeywordType;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public String getSearchNodeAction() {
		return searchNodeAction;
	}
	public void setSearchNodeAction(String searchNodeAction) {
		this.searchNodeAction = searchNodeAction;
	}
	public String getSearchCallback() {
		return searchCallback;
	}
	public void setSearchCallback(String searchCallback) {
		this.searchCallback = searchCallback;
	}
	public String getSearchVanPaymentStatus() {
		return searchVanPaymentStatus;
	}
	public void setSearchVanPaymentStatus(String searchVanPaymentStatus) {
		this.searchVanPaymentStatus = searchVanPaymentStatus;
	}
	public String getSearchPaymentStatus() {
		return searchPaymentStatus;
	}
	public void setSearchPaymentStatus(String searchPaymentStatus) {
		this.searchPaymentStatus = searchPaymentStatus;
	}
	public String getSearchPaymentType() {
		return searchPaymentType;
	}
	public void setSearchPaymentType(String searchPaymentType) {
		this.searchPaymentType = searchPaymentType;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public Integer getMembershipRequestNo() {
		return membershipRequestNo;
	}
	public void setMembershipRequestNo(Integer membershipRequestNo) {
		this.membershipRequestNo = membershipRequestNo;
	}
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	public Integer getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(Integer paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getGradeType() {
		return gradeType;
	}
	public void setGradeType(String gradeType) {
		this.gradeType = gradeType;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getRegType() {
		return regType;
	}
	public void setRegType(String regType) {
		this.regType = regType;
	}
	public Integer getRegAdminNo() {
		return regAdminNo;
	}
	public void setRegAdminNo(Integer regAdminNo) {
		this.regAdminNo = regAdminNo;
	}

	public String getPaymentTransactionType() {
		return paymentTransactionType;
	}
	public void setPaymentTransactionType(String paymentTransactionType) {
		this.paymentTransactionType = paymentTransactionType;
	}

	
	public String getPaymentApprovalStatus() {
		return paymentApprovalStatus;
	}
	public void setPaymentApprovalStatus(String paymentApprovalStatus) {
		this.paymentApprovalStatus = paymentApprovalStatus;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getAffiliateSerial() {
		return affiliateSerial;
	}
	public void setAffiliateSerial(String affiliateSerial) {
		this.affiliateSerial = affiliateSerial;
	}
	public String getSearchApiServiceType() {
		return searchApiServiceType;
	}
	public void setSearchApiServiceType(String searchApiServiceType) {
		this.searchApiServiceType = searchApiServiceType;
	}
	public String getSearchApiServiceStatus() {
		return searchApiServiceStatus;
	}
	public void setSearchApiServiceStatus(String searchApiServiceStatus) {
		this.searchApiServiceStatus = searchApiServiceStatus;
	}

}