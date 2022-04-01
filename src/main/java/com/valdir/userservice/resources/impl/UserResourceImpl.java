package com.valdir.userservice.resources.impl;

import com.valdir.userservice.models.dtos.UserDTO;
import com.valdir.userservice.entities.User;
import com.valdir.userservice.mappers.UserMapper;
import com.valdir.userservice.resources.UserResource;
import com.valdir.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
}
