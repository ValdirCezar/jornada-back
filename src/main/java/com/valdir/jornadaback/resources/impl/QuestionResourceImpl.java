package com.valdir.jornadaback.resources.impl;

import com.valdir.jornadaback.entities.Question;
import com.valdir.jornadaback.mappers.QuestionMapper;
import com.valdir.jornadaback.models.dtos.QuestionDTO;
import com.valdir.jornadaback.resources.QuestionResource;
import com.valdir.jornadaback.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.valdir.jornadaback.utils.constants.Paths.ID;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequiredArgsConstructor
public class QuestionResourceImpl implements QuestionResource {

    private final QuestionService service;
    private final QuestionMapper mapper;

    @Override
    public ResponseEntity<QuestionDTO> findById(Long id) {
        Question obj = service.findById(id);
        return ResponseEntity.ok().body(mapper.toDTO(obj));
    }

    @Override
    public ResponseEntity<Page<QuestionDTO>> findAll(Integer page, Integer size, String direction, String orderBy) {
        Page<Question> list = service.findPage(page, size, direction, orderBy);
        return ResponseEntity.ok().body(list.map(mapper::toDTO));
    }

    @Override
    public ResponseEntity<QuestionDTO> create(QuestionDTO dto) {
        Question obj = service.create(dto);
        URI uri = fromCurrentRequest().path(ID).buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.toDTO(obj));
    }

    @Override
    public ResponseEntity<QuestionDTO> update(QuestionDTO dto, Long id) {
        Question obj = service.update(dto, id);
        return ResponseEntity.ok().body(mapper.toDTO(obj));
    }

}