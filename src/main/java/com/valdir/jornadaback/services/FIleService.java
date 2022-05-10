package com.valdir.jornadaback.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.ArrayList;

@Service
public interface FIleService {
    URI uploadFile(MultipartFile multipartFile, Long classId);
    ArrayList<String> listFilesOnPathS3(String bucketName, String pathName);
}
