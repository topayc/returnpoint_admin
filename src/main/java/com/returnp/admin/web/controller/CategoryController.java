package com.returnp.admin.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.returnp.admin.code.CodeDefine;
import com.returnp.admin.dto.command.CategoryCommand;
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.SingleDataObjectResponse;
import com.returnp.admin.model.Category;
import com.returnp.admin.service.interfaces.CategoryService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("categoryFormInfo")
public class CategoryController extends ApplicationController{
	
	@Autowired CategoryService categoryService;
	@Autowired SearchService searchService;
	
	@RequestMapping(value = "/category/form/createForm", method = RequestMethod.GET)
	public String formCategoryRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "categoryNo", required = true,defaultValue = "1") int categoryNo,
			@RequestParam(value = "categoryLevel", required = true,defaultValue = "1") String categoryLevel,
			@RequestParam(value = "parentCategoryNo", defaultValue = "0") int parentCategoryNo,
			@RequestParam(value = "parentCategoryName", defaultValue = "0") String parentCategoryName,
			@RequestParam(value = "parentCategoryNameEn", defaultValue = "0") String parentCategoryNameEn,
			Model model){

		model.addAttribute("categoryStatusList", CodeDefine.getCategoryStatusList());
		model.addAttribute("categoryLevel", categoryLevel);
		model.addAttribute("parentCategoryNo", parentCategoryNo);
		model.addAttribute("parentCategoryName", parentCategoryName);
		model.addAttribute("parentCategoryNameEn", parentCategoryNameEn);
	
		if (action.equals("create")) {
		}else if (action.equals("modify")){
			model.addAttribute("categoryFormInfo", this.categoryService.selectByPrimaryKey(categoryNo));
		}
		return "template/form/createCategory";
	}
	
	@ResponseBody
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public  BaseResponse findCategories(
			Category category, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		ArrayListResponse<Category> res = new ArrayListResponse<Category>();
		res.setRows(this.searchService.findCategories(category));
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/category/get", method = RequestMethod.GET)
	public  BaseResponse getCategoryCommand(
			int categoryNo , 
			Model model) {
		CategoryCommand commandCond = new CategoryCommand();
		commandCond.setCategoryNo(categoryNo);

		SingleDataObjectResponse<CategoryCommand> res = new SingleDataObjectResponse<CategoryCommand>();
		ArrayList<CategoryCommand> commandList = this.searchService.findCategoryCommands(commandCond);
		if (commandList.size() < 1 || commandList.size()> 1) {
			this.setErrorResponse(res,"잘못된 요청입니다");
		}else {
			res.setData(this.searchService.findCategoryCommands(commandCond).get(0));
			this.setSuccessResponse(res);
		}
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/category/create", method = RequestMethod.POST)
	public  BaseResponse createCategory(
			Category category, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		category.setCategoryCode("C1");
		if ("1".equals(category.getCategoryLevel())) {
			category.setParentCategoryNo(0);
		}
		this.categoryService.insert(category);
		BaseResponse res = new BaseResponse();
		this.setSuccessResponse(res,"생성 완료");
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/category/update", method = RequestMethod.POST)
	public  BaseResponse udpateCategory(
			@ModelAttribute("categoryFormInfo") Category category,
			SessionStatus sessionStatus, BindingResult result, HttpSession httpSession, Model model) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getDefaultMessage());
			}
		}
		
		BaseResponse res = new BaseResponse();
		this.categoryService.updateByPrimaryKey(category);
		this.setSuccessResponse(res, "수정 완료");
		sessionStatus.setComplete();
		return res;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/category/delete", method = RequestMethod.POST)
	public  BaseResponse deleteCategory( 
			int  categoryNo, 
			int categoryLevel, 
			Model model) {
		BaseResponse res = new BaseResponse();
		this.categoryService.deleteByPrimaryKey(categoryNo);
		
		/* 대카테고리 삭제 일 경우 하부 카테고리도 삭제*/
		if (categoryLevel == 1) {
			CategoryCommand commandCond = new CategoryCommand();
			commandCond.setParentCategoryNo(categoryNo);
			this.searchService.deleteCategory(commandCond);
		}
		this.setSuccessResponse(res, "삭제 완료");
		return res;
	}
	
}
