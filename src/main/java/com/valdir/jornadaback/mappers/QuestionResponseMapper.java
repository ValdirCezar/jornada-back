package com.valdir.jornadaback.mappers;

import com.valdir.jornadaback.entities.QuestionResponse;
import com.valdir.jornadaback.models.dtos.QuestionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionResponseMapper {

    QuestionResponseDTO toDTO(QuestionResponse entity);

    @Mapping(target = "id", ignore = true)
    QuestionResponse toEntity(QuestionResponseDTO dto);

    QuestionResponse updateFromDTO(QuestionResponseDTO dto);

}
