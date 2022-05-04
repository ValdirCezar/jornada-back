package com.valdir.jornadaback.entities;

import com.valdir.jornadaback.models.enumerations.ProfileEnum;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_USER")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Size(max = 125)
    private String name;

    private String cpf;

    @Column(unique = true)
    private String email;

    @Size(max = 60)
    private String password;

    @Size(max = 10000)
    private String description;
    private Integer age;
    private Float score;
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private ProfileEnum profile;

    @ManyToMany(mappedBy = "users")
    private Set<Course> courses = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User person = (User) o;
        return id != null && Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}