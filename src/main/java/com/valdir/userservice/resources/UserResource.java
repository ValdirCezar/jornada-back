package com.valdir.userservice.resources;

import com.valdir.userservice.models.dtos.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.valdir.userservice.utils.constants.Paths.ID;
import static com.valdir.userservice.utils.constants.Paths.V1_USERS;

@RequestMapping(value = V1_USERS)
public interface UserResource {

    @GetMapping(value = ID)
    ResponseEntity<UserDTO> findById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<Page<UserDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy
    );

    @PostMapping
    ResponseEntity<Void> create(@Valid @RequestBody UserDTO dto);

    @PutMapping(value = ID)
    ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO dto, @PathVariable Long id);
}
