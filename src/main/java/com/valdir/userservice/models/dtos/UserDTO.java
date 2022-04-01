package com.valdir.userservice.models.dtos;

import com.valdir.userservice.models.enumerations.ProfileEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String password;
    private String description;
    private Integer age;
    private Float score;
    private ProfileEnum profile;
}
