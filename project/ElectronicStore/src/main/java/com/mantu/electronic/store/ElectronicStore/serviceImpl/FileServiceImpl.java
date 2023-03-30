package com.mantu.electronic.store.ElectronicStore.serviceImpl;

import com.mantu.electronic.store.ElectronicStore.exceptions.BadApiRequestException;
import com.mantu.electronic.store.ElectronicStore.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class FileServiceImpl implements FileService {
    private Logger logger= LoggerFactory.getLogger(FileServiceImpl.class);
//    private String extension;

    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {
        String originalFilename = file.getOriginalFilename();
        logger.info("Filename:{}", originalFilename);
        String filename = UUID.randomUUID().toString();
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileNameWithExtension = filename + filename;
        String fullpathWithFileName = path + File.separator + fileNameWithExtension;
        if (filename.equalsIgnoreCase(".png") || filename.equalsIgnoreCase(".jpeg")) {
            //file save
            File folder=new File(path);
            if(folder.exists()){
                //create folder
                folder.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(fullpathWithFileName));
            return fileNameWithExtension;
    }else{
        throw new BadApiRequestException("File with this "+filename+"not allowed");
        }

    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        String fullpath=path+File.separator+name;
        InputStream inputStream=new FileInputStream(fullpath);
        return inputStream;
    }


}
