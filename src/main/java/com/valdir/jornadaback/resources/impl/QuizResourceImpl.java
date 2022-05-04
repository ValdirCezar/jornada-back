package com.valdir.jornadaback.resources.impl;

import com.valdir.jornadaback.entities.Quiz;
import com.valdir.jornadaback.mappers.QuizMapper;
import com.valdir.jornadaback.models.dtos.QuizDTO;
import com.valdir.jornadaback.resources.QuizResource;
import com.valdir.jornadaback.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.valdir.jornadaback.utils.constants.Paths.ID;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequiredArgsConstructor
public class QuizResourceImpl implements QuizResource {

    private final QuizService service;
    private final QuizMapper mapper;

    @Override
    public ResponseEntity<QuizDTO> findById(Long id) {
        Quiz obj = service.findById(id);
        return ResponseEntity.ok().body(mapper.toDTO(obj));
    }

    @Override
    public ResponseEntity<Page<QuizDTO>> findAll(Integer page, Integer size, String direction, String orderBy) {
        Page<Quiz> list = service.findPage(page, size, direction, orderBy);
        return ResponseEntity.ok().body(list.map(mapper::toDTO));
    }

    @Override
    public ResponseEntity<QuizDTO> create(QuizDTO dto) {
        Quiz obj = service.create(dto);
        URI uri = fromCurrentRequest().path(ID).buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.toDTO(obj));
    }

    @Override
    public ResponseEntity<QuizDTO> update(QuizDTO dto, Long id) {
        Quiz obj = service.update(dto, id);
        return ResponseEntity.ok().body(mapper.toDTO(obj));
    }

}