package com.valdir.jornadaback.services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.valdir.jornadaback.services.S3Service;
import com.valdir.jornadaback.services.exceptions.FileException;
import com.valdir.jornadaback.services.exceptions.FileNotSupportedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class S3ServiceImpl implements S3Service {

    private final AmazonS3 s3Client;

    @Value("${aws.s3_bucket}")
    private String bucketName;

    @Override
    public URI uploadFile(MultipartFile file, Long classId)  {
        try {
            String fileName = file.getOriginalFilename();
            verifyExtension(fileName);
            InputStream inputStream = file.getInputStream();
            String contentType = file.getContentType();
            return uploadFile(fileName, inputStream, contentType, classId);
        } catch (IOException e) {
            throw new FileException("Erro de IO" + e.getMessage());
        }
    }

    @Override
    public URI uploadFile(String fileName, InputStream inputStream, String contentType, Long classId) {
        try {
            var metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            s3Client.putObject(bucketName + "/classes/classId-" + classId, fileName, inputStream, metadata);
            return s3Client.getUrl(bucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new FileException("Erro ao converter URL para URI");
        }
    }

    private void verifyExtension(String fileName) {
        var permittedExt = List.of("pdf", "jpeg", "jpg", "docx");
        var ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        if(!permittedExt.contains(ext)) {
            throw new FileNotSupportedException("Only pdf, docx, jpeg and jpg files are allowed");
        }
    }

}