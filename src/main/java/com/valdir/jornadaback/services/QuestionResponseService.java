package com.valdir.jornadaback.services;

import com.valdir.jornadaback.entities.QuestionResponse;
import com.valdir.jornadaback.models.dtos.QuestionResponseDTO;
import org.springframework.data.domain.Page;

public interface QuestionResponseService {

    QuestionResponse findById(Long id);

    Page<QuestionResponse> findPage(Integer page, Integer size, String direction, String orderBy);

    QuestionResponse create(QuestionResponseDTO dto);

    QuestionResponse update(QuestionResponseDTO dto, Long id);

    void delete(Long id);
}
