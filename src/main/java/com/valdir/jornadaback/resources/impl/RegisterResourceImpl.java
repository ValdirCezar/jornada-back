package com.valdir.jornadaback.resources.impl;

import com.valdir.jornadaback.resources.RegisterResource;
import com.valdir.jornadaback.services.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterResourceImpl implements RegisterResource {

    private final RegisterService service;

    @Override
    public ResponseEntity<Void> registerUserAndCourse(Long userId, Long courseId) {
        service.registerUserAndCourse(userId, courseId);
        return ResponseEntity.ok().build();
    }
}
