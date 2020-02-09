package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;

@Transactional
public interface UploadFileService {

	public ReturnpBaseResponse uploadSalesFile(MultipartFile uploadFile, String saveDir);

	public ReturnpBaseResponse uploadCapFile(MultipartFile capFile, String paymentTransactionType, String realPath);
}
