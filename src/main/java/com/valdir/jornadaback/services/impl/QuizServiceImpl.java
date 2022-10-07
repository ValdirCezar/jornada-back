package com.valdir.jornadaback.services.impl;

import com.valdir.jornadaback.entities.Quiz;
import com.valdir.jornadaback.mappers.QuizMapper;
import com.valdir.jornadaback.models.dtos.QuizDTO;
import com.valdir.jornadaback.repositories.QuizRepository;
import com.valdir.jornadaback.services.ClassService;
import com.valdir.jornadaback.services.QuizService;
import com.valdir.jornadaback.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.valdir.jornadaback.utils.constants.Messages.OBJECT_NOT_FOUND_MESSAGE;
import static java.lang.String.format;
import static org.springframework.data.domain.Sort.Direction.valueOf;

@Log4j2
@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository repository;
    private final QuizMapper mapper;
    private final ClassService classService;

    @Override
    public Quiz findById(Long id) {
        Optional<Quiz> course = repository.findById(id);
        return course.orElseThrow(
                () -> new ObjectNotFoundException(format(
                        OBJECT_NOT_FOUND_MESSAGE, id, Quiz.class.getSimpleName()
                ))
        );
    }

    @Override
    public Page<Quiz> findPage(Integer page, Integer size, String direction, String orderBy) {
        return repository.findAll(PageRequest.of(page, size, valueOf(direction), orderBy));
    }

    @Override
    public Quiz create(QuizDTO dto) {
        return repository.save(mapper.toEntity(dto, classService));
    }

    @Override
    public Quiz update(QuizDTO dto, Long id) {
        dto.setId(id);
        Quiz course = findById(id);
        course = mapper.updateFromDTO(dto, classService);
        return repository.save(course);
    }

}
