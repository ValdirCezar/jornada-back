package com.valdir.userservice.models.dtos;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
@Setter
@AllArgsConstructor
public class CourseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String description;
}
