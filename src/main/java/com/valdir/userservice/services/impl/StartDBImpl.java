package com.valdir.userservice.services.impl;

import com.valdir.userservice.entities.User;
import com.valdir.userservice.enumerations.ProfileEnum;
import com.valdir.userservice.repositories.UserRepository;
import com.valdir.userservice.services.StartDB;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.valdir.userservice.enumerations.ProfileEnum.ADMIN;

@RequiredArgsConstructor
@Service
public class StartDBImpl implements StartDB {

    private final UserRepository repository;

    @Override
    public void startDB() {
        repository.saveAll(List.of(
                new User(null, "Valdir cezar", "09129161924", "valdir@mail.com", "Aluno Senai", "Description", 25, 24.5f, ADMIN)
        ));
    }
}
