package com.valdir.userservice.services.impl;

import com.valdir.userservice.entities.Course;
import com.valdir.userservice.mappers.CourseMapper;
import com.valdir.userservice.models.dtos.CourseDTO;
import com.valdir.userservice.repositories.CourseRepository;
import com.valdir.userservice.services.CourseService;
import com.valdir.userservice.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;
import static org.springframework.data.domain.Sort.Direction.valueOf;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;
    private final CourseMapper mapper;

    @Override
    public CourseDTO findById(Long id) {
        Optional<Course> course = repository.findById(id);

        return mapper.toDTO(course.orElseThrow(
                () -> new ObjectNotFoundException(format("Object not found exception! Id: %d", id))
        ));
    }

    @Override
    public Page<CourseDTO> findPage(Integer page, Integer size, String direction, String orderBy) {
        Page<Course> list = repository.findAll(PageRequest.of(page, size, valueOf(direction), orderBy));
        return list.map(mapper::toDTO);
    }

    @Override
    public CourseDTO create(CourseDTO dto) {
        dto.setId(null);
        Course course = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(course);
    }

    @Override
    public CourseDTO update(CourseDTO dto, Long id) {
        dto.setId(id);
        Course course = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(course);
    }

}
