package com.returnp.admin.dto.command;

import com.returnp.admin.model.Category;

public class CategoryCommand extends Category {
	private String parentCategoryName;
	private String parentCategoryNameEn
	
	;
	public String getParentCategoryName() {
		return parentCategoryName;
	}
	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}
	public String getParentCategoryNameEn() {
		return parentCategoryNameEn;
	}
	public void setParentCategoryNameEn(String parentCategoryNameEn) {
		this.parentCategoryNameEn = parentCategoryNameEn;
	}
}
