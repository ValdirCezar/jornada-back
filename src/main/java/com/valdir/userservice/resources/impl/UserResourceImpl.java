package com.valdir.userservice.resources.impl;

import com.valdir.userservice.entities.User;
import com.valdir.userservice.mappers.UserMapper;
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
    private final UserMapper mapper;

    @Override
    public ResponseEntity<UserDTO> findById(Long id) {
        User user = service.findById(id);
        return ResponseEntity.ok().body(mapper.entityToDTO(user));
    }

    @Override
    public ResponseEntity<Page<UserDTO>> findAll(Integer page, Integer size, String direction, String orderBy) {
        Page<User> list = service.findPage(page, size, direction, orderBy);
        return ResponseEntity.ok().body(list.map(mapper::entityToDTO));
    }

    @Override
    public ResponseEntity<Void> create(UserDTO dto) {
        User user = service.create(dto);
        URI uri = fromCurrentRequest().path(ID).buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<UserDTO> update(UserDTO dto, Long id) {
        User user = service.update(dto, id);
        return ResponseEntity.ok().body(mapper.entityToDTO(user));
    }

}
