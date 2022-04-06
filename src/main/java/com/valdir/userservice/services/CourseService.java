package com.valdir.userservice.services;

import com.valdir.userservice.entities.Course;
import com.valdir.userservice.models.dtos.CourseDTO;
import org.springframework.data.domain.Page;

public interface CourseService {

    Course findById(Long id);

    Page<Course> findPage(Integer page, Integer size, String direction, String orderBy);

    Course create(CourseDTO dto);

    Course update(CourseDTO dto, Long id);
}
