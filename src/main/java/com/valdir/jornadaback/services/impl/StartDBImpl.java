package com.valdir.jornadaback.services.impl;

import com.valdir.jornadaback.entities.*;
import com.valdir.jornadaback.entities.Class;
import com.valdir.jornadaback.models.enumerations.ProfileEnum;
import com.valdir.jornadaback.repositories.*;
import com.valdir.jornadaback.services.StartDB;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class
StartDBImpl implements StartDB {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ClassRepository classRepository;
    private final QuizRepository quizRepository;
    private final BCryptPasswordEncoder encoder;
    private final AlternativeRepository alternativeRepository;
    private final QuestionRepository questionRepository;

    @Override
    public void startDB() {

        Course course = Course.builder()
                .id(null)
                .name("Course")
                .description("Test course")
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

        var userSaved = userRepository.save(user);

        course.setCreatorId(userSaved.getId());
        courseRepository.save(course);

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


        Alternative alternative1 = Alternative.builder().id(1L).description("Alternativa 1").build();
        Alternative alternative2 = Alternative.builder().id(2L).description("Alternativa 2").build();
        Alternative alternative3 = Alternative.builder().id(3L).description("Alternativa 3").build();
        Alternative alternative4 = Alternative.builder().id(4L).description("Alternativa 4").build();
        Alternative alternative5 = Alternative.builder().id(5L).description("Alternativa 5").build();
        Alternative alternative6 = Alternative.builder().id(6L).description("Alternativa 6").build();

        Question question = Question.builder()
                .id(null)
                .title("Questão 1")
                .alternatives(Set.of(alternative1, alternative2, alternative3, alternative4, alternative5, alternative6))
                .correctAlternative(Integer.parseInt(alternative1.getId().toString()))
                .quiz(quiz)
                .build();

        alternative1.setQuestion(question);
        alternative2.setQuestion(question);
        alternative3.setQuestion(question);
        alternative4.setQuestion(question);
        alternative5.setQuestion(question);
        alternative6.setQuestion(question);

        questionRepository.saveAll(List.of(question));
        alternativeRepository.saveAll(List.of(alternative1, alternative2, alternative3, alternative4, alternative5, alternative6));

    }
}
