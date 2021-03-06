package com.valdir.jornadaback.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class aClass;

    @OneToMany(mappedBy = "quiz")
    private Set<Question> questions = new HashSet<>();
}
