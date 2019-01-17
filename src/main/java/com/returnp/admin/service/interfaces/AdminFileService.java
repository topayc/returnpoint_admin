package com.returnp.admin.service.interfaces;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.returnp.admin.model.AdminFile;

@Transactional
public interface AdminFileService {

	public void uploadAdminFile(String uploadNodeFileType, String uploadNodeType, MultipartFile mFile);
	public ArrayList<AdminFile> findAdminFiles(AdminFile adminFile);
	public void deleteAdminFile(AdminFile adminFile);
}
