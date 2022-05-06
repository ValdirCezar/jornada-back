package com.valdir.jornadaback.utils;

import com.valdir.jornadaback.entities.Class;

public class MockUtils {

    public static Class getSingleClass() {
        return Class.builder()
                .id(10L)
                .name("Test name")
                .description("Test description")
                .build();
    }

}
