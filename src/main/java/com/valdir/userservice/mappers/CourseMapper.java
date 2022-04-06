package com.valdir.userservice.mappers;

import com.valdir.userservice.entities.Course;
import com.valdir.userservice.models.dtos.CourseDTO;

public interface CourseMapper {

    CourseDTO toDTO(Course dto);
    Course toEntity(CourseDTO dto);
}
