package com.valdir.jornadaback.services.impl;

import com.valdir.jornadaback.entities.Question;
import com.valdir.jornadaback.mappers.QuestionMapper;
import com.valdir.jornadaback.models.dtos.QuestionDTO;
import com.valdir.jornadaback.repositories.QuestionRepository;
import com.valdir.jornadaback.services.QuestionService;
import com.valdir.jornadaback.services.QuizService;
import com.valdir.jornadaback.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.valdir.jornadaback.utils.constants.Messages.OBJECT_NOT_FOUND_MESSAGE;
import static java.lang.String.format;
import static org.springframework.data.domain.Sort.Direction.valueOf;

@RequiredArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {
    
    private final QuestionRepository repository;
    private final QuestionMapper mapper;
    private final QuizService quizService;

    @Override
    public Question findById(Long id) {
        Optional<Question> obj = repository.findById(id);
        return obj.orElseThrow(
                () -> new ObjectNotFoundException(format(
                        OBJECT_NOT_FOUND_MESSAGE, id, Question.class.getSimpleName()
                ))
        );
    }

    @Override
    public Page<Question> findPage(Integer page, Integer size, String direction, String orderBy) {
        return repository.findAll(PageRequest.of(page, size, valueOf(direction), orderBy));
    }

    @Override
    public Question create(QuestionDTO dto) {
        Question obj = mapper.toEntity(dto);
        obj.setQuiz(quizService.findById(dto.getQuizId()));
        return repository.save(obj);
    }

    @Override
    public Question update(QuestionDTO dto, Long id) {
        dto.setId(id);
        findById(id);
        return repository.save(mapper.updateFromDTO(dto, quizService));
    }
}
