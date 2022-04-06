package com.valdir.userservice.services;

import com.valdir.userservice.entities.User;
import com.valdir.userservice.models.dtos.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findById(Long id);

    Page<User> findPage(Integer page, Integer size, String direction, String orderBy);

    User create(UserDTO dto);

    User update(UserDTO dto, Long id);
}
