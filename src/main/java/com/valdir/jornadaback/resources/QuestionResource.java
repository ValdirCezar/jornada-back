package com.valdir.jornadaback.resources;

import com.valdir.jornadaback.models.dtos.QuestionDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.valdir.jornadaback.utils.constants.Paths.*;

@RequestMapping(value = V1_QUESTIONS)
public interface QuestionResource {

    @GetMapping(value = ID)
    ResponseEntity<QuestionDTO> findById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<Page<QuestionDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "title") String orderBy
    );

    @PostMapping
    ResponseEntity<QuestionDTO> create(@Valid @RequestBody QuestionDTO dto);

    @PutMapping(value = ID)
    ResponseEntity<QuestionDTO> update(@Valid @RequestBody QuestionDTO dto, @PathVariable Long id);
}