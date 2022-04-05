package com.valdir.userservice.services.impl;

import com.valdir.userservice.entities.User;
import com.valdir.userservice.repositories.UserRepository;
import com.valdir.userservice.services.StartDB;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.valdir.userservice.models.enumerations.ProfileEnum.ADMIN;

@RequiredArgsConstructor
@Service
public class StartDBImpl implements StartDB {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public void startDB() {
        repository.saveAll(List.of(
                new User(null, "Valdir cezar", "09129161924", "valdir@mail.com", encoder.encode("123"), "Description", 25, 24.5f, ADMIN)
        ));
    }
}
