package com.valdir.userservice.resources;

import com.valdir.userservice.models.dtos.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.valdir.userservice.utils.constants.Paths.ID;
import static com.valdir.userservice.utils.constants.Paths.V1_USERS;

@RequestMapping(value = V1_USERS)
public interface UserResource {

    @GetMapping(value = ID)
    ResponseEntity<UserDTO> findById(@PathVariable Long id);
}
