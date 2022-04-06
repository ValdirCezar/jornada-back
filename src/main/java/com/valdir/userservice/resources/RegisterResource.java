package com.valdir.userservice.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.valdir.userservice.utils.constants.Paths.V1_REGISTERS;

@RequestMapping(value = V1_REGISTERS)
public interface RegisterResource {

    @PostMapping
    ResponseEntity<Void> registerUserAndCourse(
            @RequestParam Long userId,
            @RequestParam Long courseId
    );
}
