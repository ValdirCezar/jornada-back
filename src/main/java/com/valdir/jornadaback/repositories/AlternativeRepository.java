package com.valdir.jornadaback.repositories;

import com.valdir.jornadaback.entities.Alternative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlternativeRepository extends JpaRepository<Alternative, Long> {
}
