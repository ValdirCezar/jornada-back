package com.valdir.userservice.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProfileEnum {

    ADMIN(0, "ADMIN"), TEACHER(1, "TEACHER"), STUDENT(2, "STUDENT");

    private Integer code;
    private String description;
}
