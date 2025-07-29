package com.example.lms.util;

import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;
import java.util.List;

public class FileTypeValidator {
    private static final List<String> ALLOWED_TYPES = Arrays.asList(
            "application/pdf", "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    );

    public static boolean isValid(MultipartFile file) {
        return ALLOWED_TYPES.contains(file.getContentType());
    }
}
