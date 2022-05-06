package com.valdir.jornadaback.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@Service
public interface FIleService {
    URI uploadFile(MultipartFile multipartFile, Long classId);
}
