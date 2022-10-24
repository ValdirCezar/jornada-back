package com.valdir.jornadaback.resources.impl;

import com.valdir.jornadaback.entities.QuestionResponse;
import com.valdir.jornadaback.mappers.QuestionResponseMapper;
import com.valdir.jornadaback.models.dtos.QuestionResponseDTO;
import com.valdir.jornadaback.resources.QuestionResponseResource;
import com.valdir.jornadaback.services.QuestionResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionResponseResourceImpl implements QuestionResponseResource {

    private final QuestionResponseService service;
    private final QuestionResponseMapper mapper;

    @Override
    public ResponseEntity<QuestionResponseDTO> findById(final Long id) {
        final var dto = service.findById(id);
        return ResponseEntity.ok().body(
                mapper.toDTO(dto)
        );
    }

    public ResponseEntity<Page<QuestionResponseDTO>> findAll(final Integer page,
                                                 final Integer linesPerPage,
                                                 final String direction,
                                                 final String orderBy) {
        Page<QuestionResponse> list = service.findPage(page, linesPerPage, direction, orderBy);
        return ResponseEntity.ok().body(list.map(mapper::toDTO));
    }

    public ResponseEntity<QuestionResponseDTO> create(final QuestionResponseDTO dto) {
        service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<QuestionResponseDTO> update(final QuestionResponseDTO dto, final Long id) {
        return ResponseEntity.ok().body(mapper.toDTO(service.update(dto, id)));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
