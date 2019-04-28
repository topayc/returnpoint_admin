package com.returnp.admin.dto.command;

import com.returnp.admin.model.AffiliateTid;

public class AffiliateTidCommand extends AffiliateTid {
	private String affiliateName;

	public String getAffiliateName() {
		return affiliateName;
	}

	public void setAffiliateName(String affiliateName) {
		this.affiliateName = affiliateName;
	}
	
}
