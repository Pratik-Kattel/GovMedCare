package com.govmedcare.utils;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class ImageUpload {

    public static String saveImage(Part imagePart, String uploadDir) throws IOException {

        // Generate unique file name
        String fileName = System.currentTimeMillis() + "_" + imagePart.getSubmittedFileName();

        // Create directory if not exists
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Full file path
        String fullPath = uploadDir + File.separator + fileName;

        // Save file
        imagePart.write(fullPath);

        // Return relative path for database
        return "uploads/" + fileName;
    }
}