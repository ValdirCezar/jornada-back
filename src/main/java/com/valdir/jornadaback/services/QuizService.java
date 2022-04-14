package com.valdir.jornadaback.services;

import com.valdir.jornadaback.entities.Quiz;
import com.valdir.jornadaback.models.dtos.QuizDTO;
import org.springframework.data.domain.Page;

public interface QuizService {

    Quiz findById(Long id);

    Page<Quiz> findPage(Integer page, Integer size, String direction, String orderBy);

    Quiz create(QuizDTO dto);

    Quiz update(QuizDTO dto, Long id);
}
