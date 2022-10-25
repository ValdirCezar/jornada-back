package com.valdir.jornadaback.services;

import com.valdir.jornadaback.entities.User;
import com.valdir.jornadaback.models.dtos.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User findById(Long id);

    Page<User> findPage(Integer page, Integer size, String direction, String orderBy);

    User create(UserDTO dto);

    User update(UserDTO dto, Long id);

    List<User> findAllUsersRegisteredOnCourse(Long creatorId);
}
