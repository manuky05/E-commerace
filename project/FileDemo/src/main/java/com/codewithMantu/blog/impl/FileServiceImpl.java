package com.codewithMantu.blog.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.UUID;

import javax.print.DocFlavor.INPUT_STREAM;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.codewithMantu.blog.services.FileService;
@Service
public class FileServiceImpl implements FileService {

	private String str;

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		
		
		//File name
		String filename=file.getOriginalFilename();
		//random name
		String randomID=UUID.randomUUID().toString();
		String fileName1=randomID.concat(filename.substring(filename.lastIndexOf(".")));
		
		
		//Fullpath
		//String filePath=path+File.separator+name;
		String filePath=path+File.separator+fileName1;
		//create folder if not created
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
			
		}
		//file copy
		Files.copy(file.getInputStream(),Paths.get(filePath));
		
		return filename;
	}


	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullpath=path+File.separator+fileName;
		InputStream is=new FileInputStream(fullpath);
		return is;
	}
}
