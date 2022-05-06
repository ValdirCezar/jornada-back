package com.valdir.jornadaback.services.impl;

import com.valdir.jornadaback.entities.Class;
import com.valdir.jornadaback.mappers.ClassMapper;
import com.valdir.jornadaback.repositories.ClassRepository;
import com.valdir.jornadaback.services.exceptions.ObjectNotFoundException;
import com.valdir.jornadaback.utils.MockUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

class ClassServiceImplTest {

    public static final long ID = 12L;

    @InjectMocks
    private ClassServiceImpl classService;

    @Mock
    private ClassRepository repository;

    @Mock
    private ClassMapper mapper;

    @Mock
    private CourseServiceImpl courseService;

    private Class aClass;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.aClass = MockUtils.getSingleClass();
    }

    @Test
    void findByIdTest() {
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(this.aClass));

        var response = classService.findById(ID);

        assertNotNull(response);
        assertNotEquals(ID, response.getId());
        assertEquals(Class.class, response.getClass());
    }

    @Test
    void findByIdWithObjectNotFoundExceptionTest() {
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            classService.findById(ID);
        } catch (Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("Object not found exception! Id: 12, Type: Class", e.getMessage());
        }
    }

    @Test
    void findPageTest() {
    }

    @Test
    void createTest() {
    }

    @Test
    void updateTest() {
    }
}