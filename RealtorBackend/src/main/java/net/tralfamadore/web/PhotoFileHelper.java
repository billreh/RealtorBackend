package net.tralfamadore.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.primefaces.event.FileUploadEvent;
import org.springframework.stereotype.Service;

@Service
public class PhotoFileHelper {
	String writeUploadedFile(FileUploadEvent event, String destDir) throws Exception {
		String imgName = event.getFile().getFileName();
		File baseDir = new File(destDir);
		
		if(!baseDir.exists()) {
			if(!baseDir.mkdir())
				throw new IOException("Cannot create base directory: " + baseDir.getAbsolutePath());
		}
		
		String filePath = destDir + File.separator + imgName;
		event.getFile().write(filePath);
		
		return imgName;
	}
	
	void copyFile(String origFile, String destDir, String fileName) throws IOException {
		File fromFile = new File(origFile);
		
		if(!fromFile.exists())
			throw new IOException("File not found: " + fromFile.getAbsolutePath());
		
		File baseDir = new File(destDir);
		
		if(!baseDir.exists()) {
			if(!baseDir.mkdir())
				throw new IOException("Cannot create base directory: " + baseDir.getAbsolutePath());
		}
		
		File destFile = new File(destDir + File.separator + fileName);
		Files.copy(fromFile.toPath(), destFile.toPath());
	}
}
