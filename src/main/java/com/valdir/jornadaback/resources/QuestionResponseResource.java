package com.valdir.jornadaback.resources;

import com.valdir.jornadaback.models.dtos.QuestionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.valdir.jornadaback.utils.constants.Paths.*;

@RequestMapping(value = V1_ALTERNATIVE_RESPONSE)
public interface QuestionResponseResource {

    @GetMapping(value = ID)
    ResponseEntity<QuestionResponseDTO> findById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<Page<QuestionResponseDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy
    );

    @PostMapping
    ResponseEntity<QuestionResponseDTO> create(@Valid @RequestBody QuestionResponseDTO dto);

    @PutMapping(value = ID)
    ResponseEntity<QuestionResponseDTO> update(@Valid @RequestBody QuestionResponseDTO dto, @PathVariable Long id);

    @DeleteMapping(value = ID)
    ResponseEntity<Void> delete(@PathVariable Long id);
}
