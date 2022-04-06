package com.valdir.jornadaback.services.impl;

import com.valdir.jornadaback.entities.Course;
import com.valdir.jornadaback.entities.User;
import com.valdir.jornadaback.mappers.CourseMapper;
import com.valdir.jornadaback.mappers.UserMapper;
import com.valdir.jornadaback.services.CourseService;
import com.valdir.jornadaback.services.RegisterService;
import com.valdir.jornadaback.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserService userService;
    private final CourseService courseService;
    private final UserMapper userMapper;
    private final CourseMapper courseMapper;

    @Override
    public void registerUserAndCourse(Long userId, Long courseId) {
        User user = userService.findById(userId);
        Course course = courseService.findById(courseId);

        user.getCourses().add(course);
        course.getUsers().add(user);

        userService.update(userMapper.entityToDTO(user), userId);
        courseService.update(courseMapper.toDTO(course), courseId);
    }

}
