package com.valdir.jornadaback.mappers;

import com.valdir.jornadaback.entities.Course;
import com.valdir.jornadaback.models.dtos.CourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDTO toDTO(Course entity);

    Course toEntity(CourseDTO dto);

    @Mapping(target = "course.users", ignore = true)
    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "classes", source = "course.classes")
    Course updateFromDTO(CourseDTO dto, Course course);
}
