package com.returnp.admin.dto.command;

import com.returnp.admin.model.Branch;

public class BranchCommand extends Branch {
	private float greenPointAmount;
	private float redPointAmount;
    private String memberName;
    private String memberEmail;
    private String recommenderName;
    private String soleDistName;
    
	public String getSoleDistName() {
		return soleDistName;
	}
	public void setSoleDistName(String soleDistName) {
		this.soleDistName = soleDistName;
	}
	public String getRecommenderName() {
		return recommenderName;
	}
	public void setRecommenderName(String recommenderName) {
		this.recommenderName = recommenderName;
	}
	public float getGreenPointAmount() {
		return greenPointAmount;
	}
	public void setGreenPointAmount(float greenPointAmount) {
		this.greenPointAmount = greenPointAmount;
	}
	public float getRedPointAmount() {
		return redPointAmount;
	}
	public void setRedPointAmount(float redPointAmount) {
		this.redPointAmount = redPointAmount;
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
    
    
}
