package com.valdir.jornadaback.mappers;

import com.valdir.jornadaback.entities.Class;
import com.valdir.jornadaback.models.dtos.ClassDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassMapper {

    Class toEntity(ClassDTO dto);

    ClassDTO toDTO(Class obj);
}
