package com.valdir.jornadaback.mappers;

import com.valdir.jornadaback.entities.Question;
import com.valdir.jornadaback.entities.Quiz;
import com.valdir.jornadaback.models.dtos.AlternativeDTO;
import com.valdir.jornadaback.models.dtos.QuestionDTO;
import com.valdir.jornadaback.services.AlternativeService;
import com.valdir.jornadaback.services.QuizService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(target = "quizId", source = "entity.quiz.id")
    QuestionDTO toDTO(Question entity);

    @Mapping(target = "id", ignore = true)
    Question toEntity(QuestionDTO dto);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "quiz", source = "dto", qualifiedByName = "getQuiz")
    Question updateFromDTO(QuestionDTO dto, @Context QuizService quizService);

    @Named("getQuiz")
    default Quiz getQuiz(QuestionDTO dto, @Context QuizService quizService) {
        return quizService.findById(dto.getQuizId());
    }

}
