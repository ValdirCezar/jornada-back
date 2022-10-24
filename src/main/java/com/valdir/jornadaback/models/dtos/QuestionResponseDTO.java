package com.valdir.jornadaback.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponseDTO {

    @JsonProperty(access = READ_ONLY)
    private Long id;

    @NotNull(message = "Field USER_ID must be not null")
    private Long userId;

    @NotNull(message = "Field QUESTION_ID must be not null")
    private Long questionId;

    @NotNull(message = "Field CORRECT_ALTERNATIVE_ID must be not null")
    private Long correctAlternativeId;
}
