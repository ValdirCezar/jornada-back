package com.valdir.jornadaback.services.impl;

import com.valdir.jornadaback.entities.Class;
import com.valdir.jornadaback.entities.Course;
import com.valdir.jornadaback.entities.Quiz;
import com.valdir.jornadaback.entities.User;
import com.valdir.jornadaback.models.enumerations.ProfileEnum;
import com.valdir.jornadaback.repositories.ClassRepository;
import com.valdir.jornadaback.repositories.CourseRepository;
import com.valdir.jornadaback.repositories.QuizRepository;
import com.valdir.jornadaback.repositories.UserRepository;
import com.valdir.jornadaback.services.StartDB;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class
StartDBImpl implements StartDB {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ClassRepository classRepository;
    private final QuizRepository quizRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public void startDB() {

        Course course = Course.builder()
                .id(null)
                .name("Course")
                .description("Test course")
                .build();

        courseRepository.save(course);

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

        userRepository.save(user);

        Class aClass = Class.builder()
                .name("Class")
                .description("Test Class")
                .build();

        course.setUsers(Collections.singleton(user));

        classRepository.save(aClass);

        Quiz quiz = Quiz.builder()
                .name("Quiz")
                .theme("Theme quiz")
                .description("Description quiz")
                .creationDate(LocalDateTime.now())
                .aClass(aClass)
                .score(4.5d)
                .build();

        quizRepository.save(quiz);

    }
}
