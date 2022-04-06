package com.valdir.jornadaback.mappers;

import com.valdir.jornadaback.entities.Course;
import com.valdir.jornadaback.entities.User;
import com.valdir.jornadaback.models.dtos.CourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDTO toDTO(Course dto);

    Course toEntity(CourseDTO dto);

    @Mapping(source = "users", target = "users")
    Course updateFromDTO(CourseDTO dto, Set<User> users);
}
