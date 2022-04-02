package com.valdir.userservice.models.dtos;

import com.valdir.userservice.models.enumerations.ProfileEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    
    private Long id;

    @NotNull(message = "Field NAME must be not null")
    @Size(min = 3, max = 125, message = "Field NAME must be 3 and 125 characters")
    private String name;

    @NotNull(message = "Field CPF must be not null")
    @CPF(message = "CPF not valid")
    private String cpf;

    @NotNull(message = "Field E-MAIL must be not null")
    @Email
    private String email;

    @NotNull(message = "Field PASSWORD must be not null")
    @Size(min = 3, max = 60, message = "Field PASSWORD must be 3 and 60 characters")
    private String password;
    private String description;
    private Integer age;
    private Float score;
    private ProfileEnum profile;
}
