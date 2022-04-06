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
@Entity(name = "TB_CLASS")
public class Class {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Size(max = 125)
    private String name;

    @Size(max = 10000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
