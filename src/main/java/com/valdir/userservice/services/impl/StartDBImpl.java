package com.valdir.userservice.services.impl;

import com.valdir.userservice.entities.Course;
import com.valdir.userservice.entities.User;
import com.valdir.userservice.models.enumerations.ProfileEnum;
import com.valdir.userservice.repositories.CourseRepository;
import com.valdir.userservice.repositories.UserRepository;
import com.valdir.userservice.services.StartDB;
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
                .courses(List.of(course))
                .build();

        course.setUsers(Collections.singletonList(user));

        userRepository.saveAll(List.of(
            user
        ));

        courseRepository.saveAll(List.of(
                course
        ));
    }
}
