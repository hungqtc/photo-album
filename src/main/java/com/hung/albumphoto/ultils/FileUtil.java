package com.hung.albumphoto.ultils;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUtil {
    private final String PHOTO_PATH = "photos/";
    private final String FILE_UPLOAD_PATH = "Files-Upload/";

    public String getUrl(MultipartFile file) throws IOException {
        String uploadDir = FILE_UPLOAD_PATH + PHOTO_PATH;
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String fileName = file.getOriginalFilename();
        try {
            InputStream in = file.getInputStream();
            Files.copy(in, uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " , ioe);
        }
        return PHOTO_PATH + fileName;
    }
}
