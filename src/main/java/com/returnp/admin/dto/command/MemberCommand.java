package com.returnp.admin.dto.command;

import com.returnp.admin.model.Member;

public class MemberCommand extends Member {
	private float greenPointAmount;
	private float redPointAmount;
	private String recommenderEmail;
	private String recommenderName;

	public String getRecommenderEmail() {
		return recommenderEmail;
	}
	public void setRecommenderEmail(String recommenderEmail) {
		this.recommenderEmail = recommenderEmail;
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
}
