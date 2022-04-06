package com.valdir.userservice.resources.impl;

import com.valdir.userservice.models.dtos.UserDTO;
import com.valdir.userservice.resources.UserResource;
import com.valdir.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.valdir.userservice.utils.constants.Paths.ID;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequiredArgsConstructor
public class UserResourceImpl implements UserResource {

    private final UserService service;

    @Override
    public ResponseEntity<UserDTO> findById(Long id) {
        UserDTO user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @Override
    public ResponseEntity<Page<UserDTO>> findAll(Integer page, Integer size, String direction, String orderBy) {
        Page<UserDTO> list = service.findPage(page, size, direction, orderBy);
        return ResponseEntity.ok().body(list);
    }

    @Override
    public ResponseEntity<Void> create(UserDTO dto) {
        dto = service.create(dto);
        URI uri = fromCurrentRequest().path(ID).buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<UserDTO> update(UserDTO dto, Long id) {
        dto = service.update(dto, id);
        return ResponseEntity.ok().body(dto);
    }

}
