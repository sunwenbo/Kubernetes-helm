package com.senses.bos.controller;

import com.senses.bos.service.ws.CryptSDK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author taodongjie
 */
@RestController
@RequestMapping("/drm")
public class DrmController {

    @Autowired
    private CryptSDK cryptSDK;


    @PostMapping("/encrypt")
    public ResponseEntity drmEncrypt(@RequestParam("uploadFile") MultipartFile zipFile) throws IOException {
        Resource resource = cryptSDK.cryptSDK(zipFile.getInputStream());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;filename=%s", "test"))
                .header(HttpHeaders.PRAGMA, "No-cache")
                .header(HttpHeaders.EXPIRES, "0")
                .header(HttpHeaders.CACHE_CONTROL, "No-cache")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
