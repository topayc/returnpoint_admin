package com.returnp.admin.dto.command;

import com.returnp.admin.model.SaleManager;

public class SaleManagerCommand extends SaleManager {
	private float greenPointAmount;
	private float redPointAmount;
    private String memberName;
    private String memberEmail;
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
