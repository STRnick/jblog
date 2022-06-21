package com.douzone.jblog.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	private static String DEFAULT_LOGO = "default.jpg";
	private static String SAVE_PATH = "/jblog-uploads";
	private static String URL_BASE = "/assets/images";

	public String restore(MultipartFile multipartfile) {
		String url = null;
		try {
			if (multipartfile.isEmpty()) {
				return url;
			}

			String originFileName = multipartfile.getOriginalFilename();
			String extName = originFileName.substring(originFileName.lastIndexOf(".") + 1);
			String saveFileName = generateSaveFileName(extName);
			long fileSize = multipartfile.getSize();

			System.out.println("#########" + originFileName);
			System.out.println("#########" + saveFileName);
			System.out.println("#########" + fileSize);

			byte[] data = multipartfile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			os.write(data);
			os.close();

			url = URL_BASE + "/" + saveFileName;

		} catch (IOException ex) {
			throw new RuntimeException("file upload error : " + ex);
		}

		return url;
	}
	
	private void generateDirectory() {
		File restoreDirectory = new File(SAVE_PATH);
		if(!restoreDirectory.exists())
			restoreDirectory.mkdirs();
	}
	
	public String defaultUrl() {
		generateDirectory();
		return URL_BASE + "/" + DEFAULT_LOGO;
	}

	private String generateSaveFileName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();

		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);

		return fileName;
	}

}