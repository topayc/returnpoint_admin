package com.returnp.admin.dto.command;

import com.returnp.admin.model.PointTransferTransaction;

public class PointTransferTransactionCommand extends PointTransferTransaction {
	private String pointTransfererName;
	private String pointTransfererEmail;
	private String pointReceiverName;
	
	public String getPointTransfererName() {
		return pointTransfererName;
	}
	public void setPointTransfererName(String pointTransfererName) {
		this.pointTransfererName = pointTransfererName;
	}
	public String getPointTransfererEmail() {
		return pointTransfererEmail;
	}
	public void setPointTransfererEmail(String pointTransfererEmail) {
		this.pointTransfererEmail = pointTransfererEmail;
	}
	public String getPointReceiverName() {
		return pointReceiverName;
	}
	public void setPointReceiverName(String pointReceiverName) {
		this.pointReceiverName = pointReceiverName;
	}
	
	
}
