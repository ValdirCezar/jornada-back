package com.valdir.jornadaback.services.impl;

import com.valdir.jornadaback.services.FIleService;
import com.valdir.jornadaback.services.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FIleService {

    private final S3Service s3Service;

    @Override
    public URI uploadFile(MultipartFile multipartFile, Long classId) {
        return s3Service.uploadFile(multipartFile, classId);
    }
}