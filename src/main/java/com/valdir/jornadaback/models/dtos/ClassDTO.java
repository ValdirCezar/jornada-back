package com.valdir.jornadaback.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassDTO {

    @JsonProperty(access = READ_ONLY)
    private Long id;

    @NotNull(message = "Field NAME must be not null")
    @Size(min = 3, max = 125, message = "Field NAME must be 3 and 125 characters")
    private String name;

    @NotNull(message = "Field DESCRIPTION must be not null")
    @Size(min = 5, max = 5000, message = "Field NAME must be 5 and 5000 characters")
    private String description;
}
