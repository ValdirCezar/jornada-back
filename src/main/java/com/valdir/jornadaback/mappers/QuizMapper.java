package com.valdir.jornadaback.mappers;

import com.valdir.jornadaback.entities.Class;
import com.valdir.jornadaback.entities.Quiz;
import com.valdir.jornadaback.models.dtos.QuizDTO;
import com.valdir.jornadaback.services.ClassService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface QuizMapper {

    @Mapping(target = "id", ignore = true)
    Quiz toEntity(QuizDTO dto);

    @Mapping(target = "classId", source = "obj.id")
    QuizDTO toDTO(Quiz obj);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "aClass", source = "dto", qualifiedByName = "getClass")
    Quiz updateFromDTO(QuizDTO dto, @Context ClassService courseService);

    @Named("getClass")
    default Class getClass(QuizDTO dto, @Context ClassService classService) {
        return classService.findById(dto.getClassId());
    }

}
