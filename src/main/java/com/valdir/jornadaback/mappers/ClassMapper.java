package com.valdir.jornadaback.mappers;

import com.valdir.jornadaback.entities.Class;
import com.valdir.jornadaback.models.dtos.ClassDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClassMapper {

    Class toEntity(ClassDTO dto);

    @Mapping(target = "courseId", source = "obj.course.id")
    ClassDTO toDTO(Class obj);

}
