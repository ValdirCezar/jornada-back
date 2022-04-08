package com.valdir.jornadaback.mappers;

import com.valdir.jornadaback.entities.Class;
import com.valdir.jornadaback.entities.Course;
import com.valdir.jornadaback.models.dtos.ClassDTO;
import com.valdir.jornadaback.services.CourseService;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ClassMapper {

    @Mapping(target = "id", ignore = true)
    Class toEntity(ClassDTO dto);

    @Mapping(target = "courseId", source = "obj.course.id")
    ClassDTO toDTO(Class obj);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "course", source = "dto", qualifiedByName = "getCourse")
    Class updateFromDTO(ClassDTO dto, @Context CourseService courseService);

    @Named("getCourse")
    default Course getCourse(ClassDTO dto, @Context CourseService courseService) {
        return courseService.findById(dto.getCourseId());
    }

}
