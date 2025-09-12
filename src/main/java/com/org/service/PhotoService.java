package com.org.service;

import com.org.entity.Photo;
import com.org.entity.PhotoFolder;
import com.org.interfaces.PhotoFolderable;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class PhotoService {
    private static final String LOCATION = "src/main/resources/img";

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    private static PhotoService instance;

    private PhotoService() {
    }

    public static synchronized PhotoService getInstance() {
        if (instance == null) {
            instance = new PhotoService();
        }
        return instance;
    }

    public boolean savePhoto(Part part, PhotoFolderable photoFolderable) {
        if (part == null || part.getSize() <= 0) {
            return false;
        }

        PhotoFolder photoFolder = extractPhotoFolder(photoFolderable);
        List<Photo> photos = photoFolder.getPhotos();

        String storedFileName = upload(part);
        if (storedFileName == null) {
            return false;
        }

        photos.add(buildPhoto(part, storedFileName));
        return true;
    }

    private PhotoFolder extractPhotoFolder(PhotoFolderable photoFolderable) {
        if (photoFolderable.getPhotoFolder() == null) {
            photoFolderable.setPhotoFolder(new PhotoFolder());
        }
        return photoFolderable.getPhotoFolder();
    }

    private String upload(Part part) {
        try {
            String cleanFileName = sanitizeFileName(part.getSubmittedFileName());
            String uniqueSuffix = FORMATTER.format(LocalDateTime.now()) + "-" + UUID.randomUUID();
            String storedFileName = cleanFileName + "-" + uniqueSuffix;

            Path target = Path.of(LOCATION, storedFileName);
            Files.copy(
                    part.getInputStream(),
                    target,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return storedFileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Photo buildPhoto(Part part, String storedFileName) {
        String suffix = "";
        int dotIndex = storedFileName.lastIndexOf('.');
        if (dotIndex >= 0) {
            suffix = storedFileName.substring(dotIndex + 1);
        }

        return Photo.builder()
                .fileName(storedFileName)
                .fileSize(part.getSize())
                .fileLocation(LOCATION)
                .suffix(suffix)
                .build();
    }

    private String sanitizeFileName(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            return "unnamed";
        }
        return fileName.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}
