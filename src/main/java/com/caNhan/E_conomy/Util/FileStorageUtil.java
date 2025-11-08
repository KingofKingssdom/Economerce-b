package com.caNhan.E_conomy.Util;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileStorageUtil {
    private static final String BASE_UPLOAD_DIR = "uploads"; // thư mục gốc

    public static String storeFile(String folder, MultipartFile file) throws IOException {

        String uploadPath = BASE_UPLOAD_DIR + File.separator + folder;

        File dir = new File(uploadPath);
        if (!dir.exists()) dir.mkdirs();


        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();


        Path filePath = Paths.get(uploadPath, fileName);
        Files.copy(file.getInputStream(), filePath);


        return  BASE_UPLOAD_DIR + "/" + folder + "/" + fileName;
    }


    public static String fullUrl(String relativePath) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()  // Lấy base URL hiện tại (vd: http://localhost:8080)
                .path(relativePath)        // Ghép phần /uploads/Product/...
                .toUriString();            // Trả thành chuỗi URL hoàn chỉnh
    }
}
