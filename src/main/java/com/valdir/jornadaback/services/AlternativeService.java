package com.valdir.jornadaback.services;

import com.valdir.jornadaback.entities.Alternative;
import com.valdir.jornadaback.models.dtos.AlternativeDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AlternativeService {

    Alternative findById(Long id);

    Page<Alternative> findPage(Integer page, Integer size, String direction, String orderBy);

    Alternative create(AlternativeDTO dto);

    Alternative update(AlternativeDTO dto, Long id);

    List<Alternative> findByQuestionId(Long questionId);
}
