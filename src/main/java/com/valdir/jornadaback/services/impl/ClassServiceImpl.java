package com.valdir.jornadaback.services.impl;

import com.valdir.jornadaback.entities.Class;
import com.valdir.jornadaback.mappers.ClassMapper;
import com.valdir.jornadaback.models.dtos.ClassDTO;
import com.valdir.jornadaback.repositories.ClassRepository;
import com.valdir.jornadaback.services.ClassService;
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
public class ClassServiceImpl implements ClassService {

    private final ClassRepository repository;
    private final ClassMapper mapper;

    @Override
    public Class findById(Long id) {
        Optional<Class> obj = repository.findById(id);
        return obj.orElseThrow(
                () -> new ObjectNotFoundException(format(
                        OBJECT_NOT_FOUND_MESSAGE, id, Class.class.getSimpleName()
                ))
        );
    }

    @Override
    public Page<Class> findPage(Integer page, Integer size, String direction, String orderBy) {
        return repository.findAll(PageRequest.of(page, size, valueOf(direction), orderBy));
    }

    @Override
    public Class create(ClassDTO dto) {
        Class obj = mapper.toEntity(dto);
        return repository.save(obj);
    }

    @Override
    public Class update(ClassDTO dto, Long id) {
        dto.setId(id);
        findById(id);
        return repository.save(mapper.toEntity(dto));
    }
}
