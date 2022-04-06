package com.valdir.userservice.resources.impl;

import com.valdir.userservice.entities.Course;
import com.valdir.userservice.mappers.CourseMapper;
import com.valdir.userservice.models.dtos.CourseDTO;
import com.valdir.userservice.resources.CourseResource;
import com.valdir.userservice.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.valdir.userservice.utils.constants.Paths.ID;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequiredArgsConstructor
public class CourseResourceImpl implements CourseResource {

    private final CourseService service;
    private final CourseMapper mapper;

    @Override
    public ResponseEntity<CourseDTO> findById(Long id) {
        Course course = service.findById(id);
        return ResponseEntity.ok().body(mapper.toDTO(course));
    }

    @Override
    public ResponseEntity<Page<CourseDTO>> findAll(Integer page, Integer size, String direction, String orderBy) {
        Page<Course> list = service.findPage(page, size, direction, orderBy);
        return ResponseEntity.ok().body(list.map(mapper::toDTO));
    }

    @Override
    public ResponseEntity<Void> create(CourseDTO dto) {
        Course course = service.create(dto);
        URI uri = fromCurrentRequest().path(ID).buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<CourseDTO> update(CourseDTO dto, Long id) {
        Course course = service.update(dto, id);
        return ResponseEntity.ok().body(mapper.toDTO(course));
    }

}
