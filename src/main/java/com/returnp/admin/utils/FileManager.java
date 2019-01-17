package com.returnp.admin.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
