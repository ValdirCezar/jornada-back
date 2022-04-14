package com.valdir.jornadaback.models.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class QuizDTO {

    private Long id;

    @NotNull(message = "Field NAME must be not null")
    @Size(min = 3, max = 125, message = "Field NAME must be 3 and 125 characters")
    private String name;

    private String description;

    private String theme;

    private final LocalDateTime creationDate = LocalDateTime.now();

    private Double score;

    @NotNull(message = "Field CLASS_ID must be not null")
    private Long classId;
}
