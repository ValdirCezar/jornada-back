package com.valdir.jornadaback.resources.impl;

import com.valdir.jornadaback.entities.Class;
import com.valdir.jornadaback.mappers.ClassMapper;
import com.valdir.jornadaback.models.dtos.ClassDTO;
import com.valdir.jornadaback.resources.ClassResource;
import com.valdir.jornadaback.services.ClassService;
import com.valdir.jornadaback.services.FIleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static com.valdir.jornadaback.utils.constants.Paths.ID;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequiredArgsConstructor
public class ClassResourceImpl implements ClassResource {

    private final ClassService service;
    private final ClassMapper mapper;
    private final FIleService fileService;

    @Value("${aws.s3_bucket}")
    private String bucketName;

    @Override
    public ResponseEntity<ClassDTO> findById(Long id) {
        Class obj = service.findById(id);
        return ResponseEntity.ok().body(mapper.toDTO(obj));
    }

    @Override
    public ResponseEntity<Page<ClassDTO>> findAll(Integer page, Integer size, String direction, String orderBy) {
        Page<Class> list = service.findPage(page, size, direction, orderBy);
        return ResponseEntity.ok().body(list.map(mapper::toDTO));
    }

    @Override
    public ResponseEntity<ClassDTO> create(ClassDTO dto) {
        Class obj = service.create(dto);
        URI uri = fromCurrentRequest().path(ID).buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.toDTO(obj));
    }

    @Override
    public ResponseEntity<ClassDTO> update(ClassDTO dto, Long id) {
        Class obj = service.update(dto, id);
        return ResponseEntity.ok().body(mapper.toDTO(obj));
    }

    @Override
    public ResponseEntity<URI> uploadFile(MultipartFile multipartFile, Long classId) {
        URI uri = fileService.uploadFile(multipartFile, classId);
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<List<String>> getFilesURI(Long classId) throws URISyntaxException {
        List<String> pathList = fileService.listFilesOnPathS3(bucketName, "classes/classId-" + classId.toString());
        return ResponseEntity.ok().body(pathList);
    }

}