package com.valdir.jornadaback.repositories;

import com.valdir.jornadaback.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByCreatorId(Long creatorId);
}
