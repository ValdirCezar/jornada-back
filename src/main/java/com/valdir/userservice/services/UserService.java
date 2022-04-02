package com.valdir.userservice.services;

import com.valdir.userservice.models.dtos.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDTO findById(Long id);
    Page<UserDTO> findPage( Integer page, Integer size, String direction, String orderBy);
    UserDTO create(UserDTO dto);
    UserDTO update(UserDTO dto, Long id);
}
