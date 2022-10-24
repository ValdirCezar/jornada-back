package com.valdir.jornadaback.repositories;

import com.valdir.jornadaback.entities.QuestionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionResponseRepository extends JpaRepository<QuestionResponse, Long> {
}
