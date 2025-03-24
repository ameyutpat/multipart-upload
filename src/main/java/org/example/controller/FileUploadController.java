package org.example.controller;

import org.example.service.OkHttpFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    @Autowired
    private OkHttpFileUploadService okHttpFileUploadService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("description") String description) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        String message = "File uploaded successfully: " + file.getOriginalFilename() + ", Description: " + description;
        return ResponseEntity.ok(message);
    }
}
