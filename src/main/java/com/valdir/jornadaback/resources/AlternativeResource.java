package com.valdir.jornadaback.resources;

import com.valdir.jornadaback.models.dtos.AlternativeDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.valdir.jornadaback.utils.constants.Paths.*;

@RequestMapping(value = V1_ALTERNATIVE)
public interface AlternativeResource {

    @GetMapping(value = ID)
    ResponseEntity<AlternativeDTO> findById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<Page<AlternativeDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "description") String orderBy
    );

    @PostMapping
    ResponseEntity<AlternativeDTO> create(@Valid @RequestBody AlternativeDTO dto);

    @PutMapping(value = ID)
    ResponseEntity<AlternativeDTO> update(@Valid @RequestBody AlternativeDTO dto, @PathVariable Long id);

    @DeleteMapping(value = ID)
    ResponseEntity<Void> delete(@PathVariable Long id);
}
