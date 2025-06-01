package com.maid.service.provider.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

public class FileUploadUtil {

    public static String saveFile(String uploadDir, String originalFileName, MultipartFile multipartFile) throws IOException {
        // Make sure directory exists
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);  // This creates folders if missing
        }

        String fileName = UUID.randomUUID().toString() + "_" + originalFileName;
        Path filePath = uploadPath.resolve(fileName);

        // Save the file
        multipartFile.transferTo(filePath.toFile());

        return fileName;
    }
}
