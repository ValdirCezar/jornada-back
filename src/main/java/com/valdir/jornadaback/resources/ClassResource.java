package com.valdir.jornadaback.resources;

import com.valdir.jornadaback.models.dtos.ClassDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static com.valdir.jornadaback.utils.constants.Paths.ID;
import static com.valdir.jornadaback.utils.constants.Paths.V1_CLASSES;

@RequestMapping(value = V1_CLASSES)
public interface ClassResource {

    @GetMapping(value = ID)
    ResponseEntity<ClassDTO> findById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<Page<ClassDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    );

    @PostMapping
    ResponseEntity<ClassDTO> create(@Valid @RequestBody ClassDTO dto);

    @PutMapping(value = ID)
    ResponseEntity<ClassDTO> update(@Valid @RequestBody ClassDTO dto, @PathVariable Long id);

    @PostMapping(value = "/uploadFile")
    ResponseEntity<URI> uploadFile(@RequestParam("file") MultipartFile multipartFile,
                                   @RequestParam("classId") Long classId);

    @GetMapping(value = "/files")
    ResponseEntity<List<String>> getFilesURI(@NonNull @RequestParam Long classId) throws URISyntaxException;
}