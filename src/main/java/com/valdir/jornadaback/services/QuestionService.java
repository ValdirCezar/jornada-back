package com.valdir.jornadaback.services;

import com.valdir.jornadaback.entities.Question;
import com.valdir.jornadaback.models.dtos.QuestionDTO;
import org.springframework.data.domain.Page;

public interface QuestionService {
    
    Question findById(Long id);

    Page<Question> findPage(Integer page, Integer size, String direction, String orderBy);

    Question create(QuestionDTO dto);

    Question update(QuestionDTO dto, Long id);
}
