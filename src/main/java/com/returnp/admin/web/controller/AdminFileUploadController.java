package com.returnp.admin.web.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.returnp.admin.code.CodeDefine;
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.dto.reponse.SearchListResponse;
import com.returnp.admin.model.AdminFile;
import com.returnp.admin.service.interfaces.AdminFileService;

@Controller
@RequestMapping("/api")
@SessionAttributes("adminFileFormInfo")
public class AdminFileUploadController extends ApplicationController {
	
	@Autowired AdminFileService adminFileSearvice;
	
	@RequestMapping(value = "/adminFileUpload/form/createFileUploadForm", method = RequestMethod.GET)
	public String formAffiliateRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action, Model model){
		model.addAttribute("nodeTypeList", CodeDefine.getNodeTypeList());
		return "template/form/file/createFileUploadForm";
	}
	
	@RequestMapping(value = "/adminFileUploads", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse getFiles() {
		SearchListResponse<AdminFile> slr = new SearchListResponse<AdminFile>();
		ArrayList<AdminFile> adminFiles = this.adminFileSearvice.findAdminFiles(new AdminFile());
		slr.setRows(adminFiles);
		slr.setTotal(adminFiles.size());
		this.setSuccessResponse(slr, "");
		return slr;
	}
	
	@RequestMapping(value = "/adminFileUpload/create", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse uploadFile(
			@RequestParam(value = "id = uploadNodeFileType", required = true)  String uploadNodeFileType,
			@RequestParam(value = "uploadNodeType", required = true)  String uploadNodeType,
			@RequestParam(value = "uploadFile", required = true) MultipartFile uploadFile ) {
		BaseResponse  res = new BaseResponse();
		return null;
	}
	
	@RequestMapping(value = "/adminFileUpload/delete", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponse removeFile(AdminFile adminFile) {
		BaseResponse  res = new BaseResponse();
		this.adminFileSearvice.deleteAdminFile(adminFile);
		this.setSuccessResponse(res, "삭제 완료");
		return res;
	}
}
