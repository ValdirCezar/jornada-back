package com.valdir.jornadaback.services.impl;

import com.valdir.jornadaback.entities.Alternative;
import com.valdir.jornadaback.mappers.AlternativeMapper;
import com.valdir.jornadaback.models.dtos.AlternativeDTO;
import com.valdir.jornadaback.repositories.AlternativeRepository;
import com.valdir.jornadaback.services.AlternativeService;
import com.valdir.jornadaback.services.QuestionService;
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
public class AlternativeServiceImpl implements AlternativeService {

    private final AlternativeRepository repository;
    private final AlternativeMapper mapper;
    private final QuestionService questionService;

    @Override
    public Alternative findById(Long id) {
        Optional<Alternative> obj = repository.findById(id);
        return obj.orElseThrow(
                () -> new ObjectNotFoundException(format(
                        OBJECT_NOT_FOUND_MESSAGE, id, Alternative.class.getSimpleName()
                ))
        );
    }

    @Override
    public Page<Alternative> findPage(Integer page, Integer size, String direction, String orderBy) {
        return repository.findAll(PageRequest.of(page, size, valueOf(direction), orderBy));
    }

    @Override
    public Alternative create(AlternativeDTO dto) {
        return repository.save(mapper.toEntity(dto, questionService));
    }

    @Override
    public Alternative update(AlternativeDTO dto, Long id) {
        dto.setId(id);
        findById(id);
        return repository.save(mapper.updateFromDTO(dto, questionService));
    }
}
