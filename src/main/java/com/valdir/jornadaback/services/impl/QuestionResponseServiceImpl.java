package com.valdir.jornadaback.services.impl;

import com.valdir.jornadaback.entities.QuestionResponse;
import com.valdir.jornadaback.mappers.QuestionResponseMapper;
import com.valdir.jornadaback.models.dtos.QuestionResponseDTO;
import com.valdir.jornadaback.repositories.QuestionResponseRepository;
import com.valdir.jornadaback.services.QuestionResponseService;
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
public class QuestionResponseServiceImpl implements QuestionResponseService {
    
    private final QuestionResponseRepository repository;
    private final QuestionResponseMapper mapper;

    @Override
    public QuestionResponse findById(Long id) {
        Optional<QuestionResponse> obj = repository.findById(id);
        return obj.orElseThrow(
                () -> new ObjectNotFoundException(format(
                        OBJECT_NOT_FOUND_MESSAGE, id, QuestionResponse.class.getSimpleName()
                ))
        );
    }

    @Override
    public Page<QuestionResponse> findPage(Integer page, Integer size, String direction, String orderBy) {
        return repository.findAll(PageRequest.of(page, size, valueOf(direction), orderBy));
    }

    @Override
    public QuestionResponse create(QuestionResponseDTO dto) {
        QuestionResponse obj = mapper.toEntity(dto);
        return repository.save(obj);
    }

    @Override
    public QuestionResponse update(QuestionResponseDTO dto, Long id) {
        dto.setId(id);
        findById(id);
        return repository.save(mapper.updateFromDTO(dto));
    }

    @Override
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
