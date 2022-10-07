package com.valdir.jornadaback.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "Field NAME must be not null")
    @Size(min = 3, max = 125, message = "Field NAME must be 3 and 125 characters")
    private String name;

    private String description;

    private String theme;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final LocalDateTime creationDate = LocalDateTime.now();

    private Double score;

    @NotNull(message = "Field CLASS_ID must be not null")
    private Long classId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<QuestionDTO> questions = new HashSet<>();
}
