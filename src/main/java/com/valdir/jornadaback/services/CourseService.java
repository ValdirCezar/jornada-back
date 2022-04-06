package com.valdir.jornadaback.services;

import com.valdir.jornadaback.entities.Course;
import com.valdir.jornadaback.models.dtos.CourseDTO;
import org.springframework.data.domain.Page;

public interface CourseService {

    Course findById(Long id);

    Page<Course> findPage(Integer page, Integer size, String direction, String orderBy);

    Course create(CourseDTO dto);

    Course update(CourseDTO dto, Long id);
}
