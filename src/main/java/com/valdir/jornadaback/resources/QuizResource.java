package com.valdir.jornadaback.resources;

import com.valdir.jornadaback.models.dtos.QuizDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.valdir.jornadaback.utils.constants.Paths.*;

@RequestMapping(value = V1_QUIZZES)
public interface QuizResource {

    @GetMapping(value = ID)
    ResponseEntity<QuizDTO> findById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<Page<QuizDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    );

    @PostMapping
    ResponseEntity<QuizDTO> create(@Valid @RequestBody QuizDTO dto);

    @PutMapping(value = ID)
    ResponseEntity<QuizDTO> update(@Valid @RequestBody QuizDTO dto, @PathVariable Long id);
}