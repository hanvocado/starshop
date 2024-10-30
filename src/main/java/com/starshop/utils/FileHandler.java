package com.starshop.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

public class FileHandler {
	public static String save(MultipartFile file) {
		try {
			String imageName = file.getOriginalFilename();
			File saveFile = new ClassPathResource("static/").getFile();
			String uploadPath = saveFile.getAbsolutePath() + File.separator + "img";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();
			Path path = Paths.get(uploadPath + File.separator + imageName);				
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			return imageName;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public static void delete(String fileName) throws IOException {
		File saveFile = new ClassPathResource("static/").getFile();
        String filePath = saveFile.getAbsolutePath() + File.separator + "img" + File.separator + fileName;

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
	}
}
