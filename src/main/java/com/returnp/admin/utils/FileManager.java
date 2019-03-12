package com.returnp.admin.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	public static File save(MultipartFile mFile , String dir) throws IllegalStateException, IOException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
		String fileName = df.format(new Date()) + "-"+ mFile.getOriginalFilename();
		
		File saveDir = new File(dir);
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}
		System.out.println("#####  업로드 저장 파일 경로");
		System.out.println(dir + File.separator + fileName);
		
		mFile.transferTo(new File(dir + File.separator + fileName));
		return new File(dir + File.separator + fileName);
	}
	
	public static File saveProductImange(MultipartFile mFile , String dir) throws IllegalStateException, IOException {
		System.out.println("업로드한 원래 파일 이름");
		System.out.println(mFile.getOriginalFilename());
		
		String orgFileFullName = mFile.getOriginalFilename();
		int pos = orgFileFullName.lastIndexOf( "." );
		
		String ext = orgFileFullName.substring( pos + 1 );
		String orgFileName = orgFileFullName.substring(0, orgFileFullName.lastIndexOf( "." ));
		
		String newFileName = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(10), ext);
		String fileName = orgFileName + "-" + newFileName;
		
		File saveDir = new File(dir);
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}
		System.out.println(newFileName);
		System.out.println("변경된 파일 이름");
		System.out.println("file.getName()");
		System.out.println(new File(dir + File.separator + fileName).getName());

		mFile.transferTo(new File(dir + File.separator + fileName));
		return new File(dir + File.separator + fileName);
	}
}
