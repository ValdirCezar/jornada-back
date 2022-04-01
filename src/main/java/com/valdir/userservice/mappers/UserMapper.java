package com.valdir.userservice.mappers;

import com.valdir.userservice.models.dtos.UserDTO;
import com.valdir.userservice.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO entityToDTO(User user);
}
