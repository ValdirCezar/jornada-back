package com.valdir.jornadaback.mappers;

import com.valdir.jornadaback.entities.Alternative;
import com.valdir.jornadaback.entities.Question;
import com.valdir.jornadaback.models.dtos.AlternativeDTO;
import com.valdir.jornadaback.services.QuestionService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AlternativeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "question", source = "dto", qualifiedByName = "getQuestion" )
    Alternative toEntity(AlternativeDTO dto, @Context QuestionService questionService);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "question", source = "dto", qualifiedByName = "getQuestion")
    Alternative updateFromDTO(AlternativeDTO dto, @Context QuestionService questionService);

    @Mapping(target = "questionId", source = "obj.question.id")
    AlternativeDTO toDTO(Alternative obj);

    @Named("getQuestion")
    default Question getQuestion(AlternativeDTO dto, @Context QuestionService questionService) {
        return questionService.findById(dto.getQuestionId());
    }

}
