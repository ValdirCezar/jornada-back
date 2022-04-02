package com.valdir.userservice.services.impl;

import com.valdir.userservice.entities.User;
import com.valdir.userservice.repositories.UserRepository;
import com.valdir.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.Sort.Direction.valueOf;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Objeto nao encontrado")
        );
    }

    @Override
    public Page<User> findPage(Integer page, Integer size, String direction, String orderBy) {
        return repository.findAll(
                PageRequest.of(page, size, valueOf(direction), orderBy)
        );
    }

    @Override
    public User create(User user) {
        user.setId(null);
        return repository.save(user);
    }

}
