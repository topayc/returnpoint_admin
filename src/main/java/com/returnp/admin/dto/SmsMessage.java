package com.returnp.admin.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import com.returnp.admin.utils.BASE64Util;

public class SmsMessage {
	private String title;
	private String message;
	private String sender;
	private String username;
	private String recipients;
	private String key;
	private String returnUel;
	private String attaches;

	public String getTitle() throws UnsupportedEncodingException {
		return URLDecoder.decode(BASE64Util.decodeString(title));
	}
	public void setTitle(String title) throws UnsupportedEncodingException {
		this.title = BASE64Util.encodeString(URLEncoder.encode(title, "utf8"));
	}
	
	public String getMessage() throws UnsupportedEncodingException {
		return BASE64Util.decodeString(message);
	}
	public void setMessage(String message) throws UnsupportedEncodingException {
		this.message = BASE64Util.encodeString(URLEncoder.encode(message, "utf8"));
	}
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) throws UnsupportedEncodingException {
		this.sender = URLEncoder.encode(sender, "utf8");
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) throws UnsupportedEncodingException {
		this.username =URLEncoder.encode(username, "utf8");
	}
	public String getRecipients() {
		return recipients;
	}
	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getReturnUel() {
		return returnUel;
	}
	public void setReturnUel(String returnUel) {
		this.returnUel = returnUel;
	}
	public String getAttaches() {
		return attaches;
	}
	public void setAttaches(String attaches) {
		this.attaches = attaches;
	}
	
}
