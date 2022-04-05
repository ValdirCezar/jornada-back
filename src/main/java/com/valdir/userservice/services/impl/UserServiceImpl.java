package com.valdir.userservice.services.impl;

import com.valdir.userservice.entities.User;
import com.valdir.userservice.mappers.UserMapper;
import com.valdir.userservice.models.dtos.UserDTO;
import com.valdir.userservice.repositories.UserRepository;
import com.valdir.userservice.services.UserService;
import com.valdir.userservice.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;
import static org.springframework.data.domain.Sort.Direction.valueOf;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDTO findById(Long id) {
        Optional<User> user = repository.findById(id);
        return mapper.entityToDTO(user.orElseThrow(
                () -> new ObjectNotFoundException(format("Object not found exception! Id: %d", id))
        ));
    }

    @Override
    public Page<UserDTO> findPage(Integer page, Integer size, String direction, String orderBy) {
        Page<User> list = repository.findAll(PageRequest.of(page, size, valueOf(direction), orderBy));
        return list.map(mapper::entityToDTO);
    }

    @Override
    public UserDTO create(UserDTO dto) {
        dto.setId(null);
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User user = repository.save(mapper.dtoToEntity(dto));
        return mapper.entityToDTO(user);
    }

    @Override
    public UserDTO update(UserDTO userDTO, Long id) {
        userDTO.setId(id);
        User user = repository.save(mapper.dtoToEntity(userDTO));
        return mapper.entityToDTO(user);
    }

}
