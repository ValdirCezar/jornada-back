package com.valdir.userservice.resources.impl;

import com.valdir.userservice.resources.RegisterResource;
import com.valdir.userservice.services.RegisterService;
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
