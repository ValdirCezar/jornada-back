package com.valdir.jornadaback.resources.impl;

import com.valdir.jornadaback.entities.Course;
import com.valdir.jornadaback.mappers.CourseMapper;
import com.valdir.jornadaback.models.dtos.CourseDTO;
import com.valdir.jornadaback.resources.CourseResource;
import com.valdir.jornadaback.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static com.valdir.jornadaback.utils.constants.Paths.ID;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequiredArgsConstructor
public class CourseResourceImpl implements CourseResource {

    private final CourseService service;
    private final CourseMapper mapper;

    @Override
    public ResponseEntity<CourseDTO> findById(Long id) {
        Course course = service.findById(id);
        CourseDTO dto = mapper.toDTO(course);
        return ResponseEntity.ok().body(dto);
    }

    @Override
    public ResponseEntity<Page<CourseDTO>> findAll(Integer page, Integer size, String direction, String orderBy) {
        Page<Course> list = service.findPage(page, size, direction, orderBy);
        return ResponseEntity.ok().body(list.map(mapper::toDTO));
    }

    @Override
    public ResponseEntity<List<CourseDTO>> findAllByUser(Long userId) {
        List<Course> courses = service.findAllByUser(userId);
        List<CourseDTO> dtoList = courses.stream().map(mapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }

    @Override
    public ResponseEntity<CourseDTO> create(CourseDTO dto) {
        Course course = service.create(dto);
        URI uri = fromCurrentRequest().path(ID).buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.toDTO(course));
    }

    @Override
    public ResponseEntity<CourseDTO> update(CourseDTO dto, Long id) {
        Course course = service.update(dto, id);
        return ResponseEntity.ok().body(mapper.toDTO(course));
    }

}
