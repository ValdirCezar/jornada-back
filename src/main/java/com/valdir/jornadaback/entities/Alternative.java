package com.valdir.jornadaback.entities;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_ALTERNATIVE")
public class Alternative {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Question question;
}
