package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.returnp.admin.dto.reponse.BaseResponse;

@Transactional
public interface UploadFileService {

	public BaseResponse uploadSalesFile(MultipartFile uploadFile, String saveDir);
}
