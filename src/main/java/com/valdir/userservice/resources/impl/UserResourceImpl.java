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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.valdir.userservice.utils.constants.Paths.ID;

@RestController
@RequiredArgsConstructor
public class UserResourceImpl implements UserResource {

    private final UserMapper mapper;
    private final UserService service;

    @Override
    public ResponseEntity<UserDTO> findById(Long id) {
        User user = service.findById(id);
        return ResponseEntity.ok().body(
                mapper.entityToDTO(user)
        );
    }

    @Override
    public ResponseEntity<Page<UserDTO>> findAll(Integer page, Integer size, String direction, String orderBy) {
        Page<User> list = service.findPage(page, size, direction, orderBy);
        return ResponseEntity.ok().body(list.map(mapper::entityToDTO));
    }

    @Override
    public ResponseEntity<Void> create(UserDTO dto) {
        User user = mapper.dotToEntity(dto);
        user = service.create(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


}
