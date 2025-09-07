package com.caNhan.E_conomy.Util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStorageUtil {
    private static final String BASE_UPLOAD_DIR = "uploads"; // Thư mục lưu ảnh
    public static String storeFile(String folder, MultipartFile file) throws IOException {
        String uploadPath = BASE_UPLOAD_DIR + File.separator + folder;

        File dir = new File(uploadPath);
        if (!dir.exists()) dir.mkdirs();

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadPath, fileName);
        Files.copy(file.getInputStream(), filePath);

        return "/" + uploadPath.replace(File.separator, "/") + "/" + fileName; // Trả về đường dẫn để lưu DB
    }
    // Lấy đường dẫn đầy đủ (có host)
    // Tạo URL đầy đủ (bao gồm cả domain + path)
    public static String fullUrl(String relativePath){
        String fullUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()  // Lấy domain hiện tại
                .path(relativePath)        // Thêm path ảnh vào
                .toUriString();            // Chuyển thành URL
        return fullUrl;
    }
}
