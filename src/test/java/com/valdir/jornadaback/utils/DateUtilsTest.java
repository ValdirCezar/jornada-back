package com.valdir.jornadaback.utils;

import com.valdir.jornadaback.services.exceptions.DataIntegrityViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DateUtilsTest {

    @Test
    void testGetAgeWithSuccess() {
        var age = DateUtils.getAge("1993-02-12");
        Assertions.assertEquals(29, age);
    }

    @Test
    void testGetAgeWithError() {
        try {
            var age = DateUtils.getAge("2033-02-12");
        } catch (Exception e) {
            Assertions.assertEquals(DataIntegrityViolationException.class, e.getClass());
            Assertions.assertEquals("Data passada como parametro inválida!", e.getMessage());
        }
    }
}