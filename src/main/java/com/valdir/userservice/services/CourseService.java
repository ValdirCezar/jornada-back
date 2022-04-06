package com.valdir.userservice.services;

import com.valdir.userservice.models.dtos.CourseDTO;
import org.springframework.data.domain.Page;

public interface CourseService {

    CourseDTO findById(Long id);

    Page<CourseDTO> findPage(Integer page, Integer size, String direction, String orderBy);

    CourseDTO create(CourseDTO dto);

    CourseDTO update(CourseDTO dto, Long id);
}
