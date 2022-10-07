package com.valdir.jornadaback.repositories;

import com.valdir.jornadaback.entities.Alternative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlternativeRepository extends JpaRepository<Alternative, Long> {

    @Query("SELECT obj FROM TB_ALTERNATIVE obj WHERE obj.question.id = ?1")
    List<Alternative> findAllByQuestionId(Long questionId);
}
