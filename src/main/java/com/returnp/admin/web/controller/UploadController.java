package com.returnp.admin.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.service.interfaces.UploadFileService;

@Controller
@RequestMapping("/api")
public class UploadController extends ApplicationController {

	@Autowired UploadFileService uploadService;
	@ResponseBody
	@RequestMapping(value = "/upload/salesFile", method = RequestMethod.POST)
	public ReturnpBaseResponse  uploadSalesFile( @RequestParam("salesFile") MultipartFile salesFile, HttpServletRequest request) {
		System.out.println("#### UploadController - uploadSalesFile");
		return this.uploadService.uploadSalesFile(salesFile, request.getSession().getServletContext().getRealPath("/salesFile"));
	}
	
	@ResponseBody
	@RequestMapping(value = "/upload/capFile", method = RequestMethod.POST)
	public ReturnpBaseResponse  uploadCap1File( 
			@RequestParam("capFile") MultipartFile capFile, @RequestParam String paymentTransactionType,   HttpServletRequest request) {
		System.out.println("#### UploadController - uploadSalesFile");
		String dir = paymentTransactionType.trim().equals("6") ? "cap1File"  : "cap2File";
		return this.uploadService.uploadCapFile(capFile, paymentTransactionType, request.getSession().getServletContext().getRealPath("/" + dir));
	}
}
