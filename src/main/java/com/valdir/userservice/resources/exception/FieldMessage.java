package com.valdir.userservice.resources.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FieldMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String message;

}