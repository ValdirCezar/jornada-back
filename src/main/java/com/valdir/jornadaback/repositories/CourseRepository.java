package com.valdir.jornadaback.repositories;

import com.valdir.jornadaback.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
