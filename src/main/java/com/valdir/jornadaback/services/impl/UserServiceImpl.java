package com.valdir.jornadaback.services.impl;

import com.valdir.jornadaback.entities.Course;
import com.valdir.jornadaback.entities.User;
import com.valdir.jornadaback.mappers.UserMapper;
import com.valdir.jornadaback.models.dtos.UserDTO;
import com.valdir.jornadaback.repositories.CourseRepository;
import com.valdir.jornadaback.repositories.UserRepository;
import com.valdir.jornadaback.services.UserService;
import com.valdir.jornadaback.services.exceptions.DataIntegrityViolationException;
import com.valdir.jornadaback.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.valdir.jornadaback.utils.constants.Messages.OBJECT_NOT_FOUND_MESSAGE;
import static java.lang.String.format;
import static java.util.Objects.nonNull;
import static org.springframework.data.domain.Sort.Direction.valueOf;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CourseRepository courseRepository;

    @Override
    public User findById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(
                () -> new ObjectNotFoundException(format(
                        OBJECT_NOT_FOUND_MESSAGE, id, User.class.getSimpleName()
                ))
        );
    }

    @Override
    public Page<User> findPage(Integer page, Integer size, String direction, String orderBy) {
        return repository.findAll(PageRequest.of(page, size, valueOf(direction), orderBy));
    }

    @Override
    public User create(UserDTO dto) {
        dto.setId(null);
        IfEmailAlreadyExistsThrowException(dto);
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return repository.save(mapper.dtoToEntity(dto));
    }

    @Override
    public User update(UserDTO dto, Long id) {
        dto.setId(id);
        User user = findById(id);
        user = mapper.updateFromDTO(dto, user.getCourses());
        return repository.save(user);
    }

    @Override
    public List<User> findAllUsersRegisteredOnCourse(Long creatorId) {
        List<Course> courses = courseRepository.findAllByCreatorId(creatorId);

        List<User> list = new ArrayList<>();
        for(Course x : courses) {
            final var users = repository.findByCourses_Id(x.getId());
            list.addAll(users);
        }

        return list;
    }

    private void IfEmailAlreadyExistsThrowException(UserDTO dto) {
        User user = repository.findByEmail(dto.getEmail());
        if(nonNull(user) || nonNull(dto.getId()) && !dto.getId().equals(user.getId())) {
            throw new DataIntegrityViolationException("E-mail already registered");
        }
    }
}
