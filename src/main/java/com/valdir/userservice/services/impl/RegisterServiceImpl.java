package com.valdir.userservice.services.impl;

import com.valdir.userservice.entities.Course;
import com.valdir.userservice.entities.User;
import com.valdir.userservice.mappers.CourseMapper;
import com.valdir.userservice.mappers.UserMapper;
import com.valdir.userservice.services.CourseService;
import com.valdir.userservice.services.RegisterService;
import com.valdir.userservice.services.UserService;
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
