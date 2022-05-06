package com.valdir.jornadaback.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URI;

@Service
public interface S3Service {
    URI uploadFile(MultipartFile file, Long classId);
    URI uploadFile(String fileName, InputStream inputStream, String contentType, Long classId);
}
