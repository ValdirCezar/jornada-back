package com.valdir.jornadaback.models.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlternativeDTO {

    private Long id;
    private String description;
    private Long questionId;

}
