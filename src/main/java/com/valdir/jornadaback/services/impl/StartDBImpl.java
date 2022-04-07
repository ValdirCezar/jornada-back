package com.valdir.jornadaback.services.impl;

import com.valdir.jornadaback.entities.Class;
import com.valdir.jornadaback.entities.Course;
import com.valdir.jornadaback.entities.User;
import com.valdir.jornadaback.models.enumerations.ProfileEnum;
import com.valdir.jornadaback.repositories.ClassRepository;
import com.valdir.jornadaback.repositories.CourseRepository;
import com.valdir.jornadaback.repositories.UserRepository;
import com.valdir.jornadaback.services.StartDB;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StartDBImpl implements StartDB {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ClassRepository classRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public void startDB() {

        Course course = Course.builder()
                .id(null)
                .name("Oficina de inovação")
                .description("Description test for Oficina de inovação")
                .build();

        User user = User.builder()
                .name("Valdir Cezar")
                .cpf("09129161924")
                .email("valdir@mail.com")
                .password(encoder.encode("123456"))
                .age(29)
                .description("Aluno senai")
                .profile(ProfileEnum.ADMIN)
                .score(5.0f)
                .courses(Collections.singleton(course))
                .build();

        course.setUsers(Collections.singleton(user));

        userRepository.saveAll(List.of(
            user
        ));

        courseRepository.saveAll(List.of(
                course
        ));

        classRepository.saveAll(List.of(
                Class.builder()
                        .id(null)
                        .name("Java OOP")
                        .description("Test description")
                        .course(course)
                        .build()
        ));
    }
}
