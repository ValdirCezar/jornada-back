package com.valdir.jornadaback.services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.valdir.jornadaback.services.FIleService;
import com.valdir.jornadaback.services.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FIleService {

    private final S3Service s3Service;
    private final AmazonS3 s3Client;

    @Override
    public URI uploadFile(MultipartFile multipartFile, Long classId) {
        return s3Service.uploadFile(multipartFile, classId);
    }

    @Override
    public ArrayList<String> listFilesOnPathS3(String bucketName, String pathName) {
        var listOfElements = new ArrayList<String>();
        var objectListing = s3Client.listObjects(bucketName, pathName);
        if (objectListing != null) {
            List<S3ObjectSummary> s3ObjectSummariesList = objectListing.getObjectSummaries();
            if (!s3ObjectSummariesList.isEmpty()) {
                for (S3ObjectSummary obj : s3ObjectSummariesList) {
                    String elementName = obj.getKey().substring(obj.getKey().lastIndexOf("/") + 1);
                    listOfElements.add(elementName);
                }
            }
        }
        return listOfElements;
    }

}