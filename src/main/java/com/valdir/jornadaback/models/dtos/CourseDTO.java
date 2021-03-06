package com.valdir.jornadaback.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Getter
@Builder
@Setter
@AllArgsConstructor
public class CourseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty(access = READ_ONLY)
    private Long id;

    @NotNull(message = "Field creatorId must be not null")
    private Long creatorId;

    @NotNull(message = "Field NAME must be not null")
    @Size(min = 3, max = 125, message = "Field NAME must be 3 and 125 characters")
    private String name;

    @NotNull(message = "Field DESCRIPTION must be not null")
    @Size(min = 5, max = 1024, message = "Field NAME must be 5 and 1024 characters")
    private String description;

    @JsonProperty(access = READ_ONLY)
    private Set<ClassDTO> classes;

}
