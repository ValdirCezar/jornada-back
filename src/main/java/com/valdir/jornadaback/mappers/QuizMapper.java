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
    @Mapping(target = "aClass", source = "dto", qualifiedByName = "getClass")
    Quiz toEntity(QuizDTO dto, @Context ClassService courseService);

    @Mapping(target = "id", source = "quiz.id")
    @Mapping(target = "classId", source = "quiz", qualifiedByName = "getClassId")
    QuizDTO toDTO(Quiz quiz, @Context ClassService classService);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "aClass", source = "dto", qualifiedByName = "getClass")
    Quiz updateFromDTO(QuizDTO dto, @Context ClassService courseService);

    @Named("getClass")
    default Class getClass(QuizDTO dto, @Context ClassService classService) {
        return classService.findById(dto.getClassId());
    }

    @Named("getClassId")
    default Long getClassId(Quiz quiz, @Context ClassService classService) {
        return classService.findById(quiz.getAClass().getId()).getId();
    }
}
