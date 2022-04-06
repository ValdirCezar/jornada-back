package com.valdir.jornadaback.mappers;

import com.valdir.jornadaback.entities.Course;
import com.valdir.jornadaback.entities.User;
import com.valdir.jornadaback.models.dtos.CourseDTO;
import com.valdir.jornadaback.models.dtos.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "user", target = "courses", qualifiedByName = "getCourseDTOList")
    UserDTO entityToDTO(User user);

    @Named("getCourseDTOList")
    default List<CourseDTO> getCourseDTOList(User user) {
        return user.getCourses().stream().map(obj -> CourseDTO.builder()
                .id(obj.getId())
                .name(obj.getName())
                .description(obj.getDescription())
                .build()
        ).collect(Collectors.toList());
    }

    User dtoToEntity(UserDTO dto);

    @Mapping(source = "courses", target = "courses")
    User updateFromDTO(UserDTO dto, Set<Course> courses);
}
