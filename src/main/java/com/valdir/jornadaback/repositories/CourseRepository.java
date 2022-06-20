package com.valdir.jornadaback.repositories;

import com.valdir.jornadaback.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByCreatorId(Long creatorId);
}
