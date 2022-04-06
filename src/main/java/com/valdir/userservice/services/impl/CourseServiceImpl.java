package com.valdir.userservice.services.impl;

import com.valdir.userservice.entities.Course;
import com.valdir.userservice.mappers.CourseMapper;
import com.valdir.userservice.models.dtos.CourseDTO;
import com.valdir.userservice.repositories.CourseRepository;
import com.valdir.userservice.services.CourseService;
import com.valdir.userservice.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;
import static org.springframework.data.domain.Sort.Direction.valueOf;

@Log4j2
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;
    private final CourseMapper mapper;

    @Override
    public Course findById(Long id) {
        Optional<Course> course = repository.findById(id);
        return course.orElseThrow(
                () -> new ObjectNotFoundException(format(
                        "Object not found exception! Id: %d, Tipe: %s", id, Course.class.getSimpleName()
                ))
        );
    }

    @Override
    public Page<Course> findPage(Integer page, Integer size, String direction, String orderBy) {
        return repository.findAll(PageRequest.of(page, size, valueOf(direction), orderBy));
    }

    @Override
    public Course create(CourseDTO dto) {
        dto.setId(null);
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public Course update(CourseDTO dto, Long id) {
        dto.setId(id);
        Course course = findById(id);
        course = mapper.updateFromDTO(dto, course.getUsers());
        return repository.save(course);
    }

}
