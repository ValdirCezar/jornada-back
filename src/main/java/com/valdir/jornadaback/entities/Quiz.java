package com.valdir.jornadaback.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_QUIZ")
public class Quiz {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Size(max = 125)
    private String name;

    @Size(max = 5000)
    private String description;

    private String theme;
    private LocalDateTime creationDate;
    private Double score;
}
