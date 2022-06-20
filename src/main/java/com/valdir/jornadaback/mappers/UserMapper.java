package com.valdir.jornadaback.mappers;

import com.valdir.jornadaback.entities.Course;
import com.valdir.jornadaback.entities.User;
import com.valdir.jornadaback.models.dtos.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Mapping(source = "user", target = "courses", qualifiedByName = "getCourseDTOList")
    UserDTO entityToDTO(User user);

//    @Named("getCourseDTOList")
//    default Set<CourseDTO> getCourseDTOList(User user) {
//        return user.getCourses().stream().map(obj -> CourseDTO.builder()
//                .id(obj.getId())
//                .name(obj.getName())
//                .description(obj.getDescription())
//                .build()
//        ).collect(Collectors.toSet());
//    }

    User dtoToEntity(UserDTO dto);

    @Mapping(source = "courses", target = "courses")
    User updateFromDTO(UserDTO dto, Set<Course> courses);
}
