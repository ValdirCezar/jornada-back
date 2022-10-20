package com.valdir.jornadaback.resources.impl;

import com.valdir.jornadaback.entities.Alternative;
import com.valdir.jornadaback.mappers.AlternativeMapper;
import com.valdir.jornadaback.models.dtos.AlternativeDTO;
import com.valdir.jornadaback.resources.AlternativeResource;
import com.valdir.jornadaback.services.AlternativeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AlternativeResourceImpl implements AlternativeResource {

    private final AlternativeService service;
    private final AlternativeMapper mapper;

    @Override
    public ResponseEntity<AlternativeDTO> findById(final Long id) {
        final var dto = service.findById(id);
        return ResponseEntity.ok().body(
                mapper.toDTO(dto)
        );
    }

    public ResponseEntity<Page<AlternativeDTO>> findAll(final Integer page,
                                                 final Integer linesPerPage,
                                                 final String direction,
                                                 final String orderBy) {
        Page<Alternative> list = service.findPage(page, linesPerPage, direction, orderBy);
        return ResponseEntity.ok().body(list.map(mapper::toDTO));
    }

    public ResponseEntity<AlternativeDTO> create(final AlternativeDTO dto) {
        service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<AlternativeDTO> update(final AlternativeDTO dto, final Long id) {
        return ResponseEntity.ok().body(mapper.toDTO(service.update(dto, id)));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
