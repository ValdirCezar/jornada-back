package com.valdir.jornadaback.services;

import com.valdir.jornadaback.entities.Class;
import com.valdir.jornadaback.models.dtos.ClassDTO;
import org.springframework.data.domain.Page;

public interface ClassService {

    Class findById(Long id);

    Page<Class> findPage(Integer page, Integer size, String direction, String orderBy);

    Class create(ClassDTO dto);

    Class update(ClassDTO dto, Long id);
}
