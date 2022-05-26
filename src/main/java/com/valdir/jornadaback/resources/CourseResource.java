package com.valdir.jornadaback.resources;

import com.valdir.jornadaback.models.dtos.CourseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.valdir.jornadaback.utils.constants.Paths.ID;
import static com.valdir.jornadaback.utils.constants.Paths.V1_COURSES;

@RequestMapping(value = V1_COURSES)
public interface CourseResource {

    @GetMapping(value = ID)
    ResponseEntity<CourseDTO> findById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<Page<CourseDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    );

    @PostMapping
    ResponseEntity<CourseDTO> create(@Valid @RequestBody CourseDTO dto);

    @PutMapping(value = ID)
    ResponseEntity<CourseDTO> update(@Valid @RequestBody CourseDTO dto, @PathVariable Long id);
}
