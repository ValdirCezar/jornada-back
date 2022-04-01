package com.valdir.userservice.services;

import com.valdir.userservice.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findById(Long id);
}
