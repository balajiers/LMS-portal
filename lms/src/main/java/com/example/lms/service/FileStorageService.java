package com.example.lms.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;

public interface FileStorageService {
    String storeFile(MultipartFile file, String subDir);
    Resource loadFileAsResource(String fileName, String subDir);
    void deleteFile(String fileName, String subDir);
    Path getFilePath(String fileName, String subDir);
}
