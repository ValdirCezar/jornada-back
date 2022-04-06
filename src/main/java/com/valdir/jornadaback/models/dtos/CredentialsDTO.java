package com.valdir.jornadaback.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsDTO {

    private String email;
    private String password;
}
