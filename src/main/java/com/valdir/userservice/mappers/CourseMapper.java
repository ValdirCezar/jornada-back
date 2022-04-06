package com.valdir.userservice.mappers;

import com.valdir.userservice.entities.Course;
import com.valdir.userservice.entities.User;
import com.valdir.userservice.models.dtos.CourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDTO toDTO(Course dto);

    Course toEntity(CourseDTO dto);

    @Mapping(source = "users", target = "users")
    Course updateFromDTO(CourseDTO dto, List<User> users);
}
