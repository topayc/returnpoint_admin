package com.returnp.admin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.returnp.admin.dao.mapper.AdminFileMapper;
import com.returnp.admin.model.AdminFile;
import com.returnp.admin.service.interfaces.AdminFileService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class AdminFileServiceImpl implements AdminFileService {

	@Autowired SearchService searchService;
	@Autowired AdminFileMapper adminFileMapper;
	
	@Override
	public void uploadAdminFile(String uploadNodeFileType, String uploadNodeType, MultipartFile mFile) {

	}
	
	@Override
	public ArrayList<AdminFile> findAdminFiles(AdminFile adminFile) {
		return this.searchService.findAdminFiles(adminFile);
	}

	@Override
	public void deleteAdminFile(AdminFile adminFile) {
		this.adminFileMapper.deleteByPrimaryKey(adminFile.getAdminFileNo());
	}
}
