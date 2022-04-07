package com.valdir.jornadaback.resources.impl;

import com.valdir.jornadaback.entities.Class;
import com.valdir.jornadaback.mappers.ClassMapper;
import com.valdir.jornadaback.models.dtos.ClassDTO;
import com.valdir.jornadaback.resources.ClassResource;
import com.valdir.jornadaback.services.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.valdir.jornadaback.utils.constants.Paths.ID;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequiredArgsConstructor
public class ClassResourceImpl  implements ClassResource {

    private final ClassService service;
    private final ClassMapper mapper;

    @Override
    public ResponseEntity<ClassDTO> findById(Long id) {
        Class obj = service.findById(id);
        return ResponseEntity.ok().body(mapper.toDTO(obj));
    }

    @Override
    public ResponseEntity<Page<ClassDTO>> findAll(Integer page, Integer size, String direction, String orderBy) {
        Page<Class> list = service.findPage(page, size, direction, orderBy);
        return ResponseEntity.ok().body(list.map(mapper::toDTO));
    }

    @Override
    public ResponseEntity<Void> create(ClassDTO dto) {
        Class obj = service.create(dto);
        URI uri = fromCurrentRequest().path(ID).buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<ClassDTO> update(ClassDTO dto, Long id) {
        Class obj = service.update(dto, id);
        return ResponseEntity.ok().body(mapper.toDTO(obj));
    }

}