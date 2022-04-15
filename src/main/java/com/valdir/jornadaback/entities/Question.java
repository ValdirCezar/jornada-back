package com.valdir.jornadaback.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_QUESTION")
public class Question {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Size(max = 125)
    private String title;

    private Integer correctAlternative;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
}
