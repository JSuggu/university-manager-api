package com.example.api_university_manager.components.student_course;

import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import org.springframework.stereotype.Service;

@Service
public class StudentCourseService {
    private final StudentCourseRepository studentCourseRepository;

    public StudentCourseService(StudentCourseRepository studentCourseRepository) {
        this.studentCourseRepository = studentCourseRepository;
    }

    public StudentCourse updateCourseStateToStudent(Long studentId, Long courseId, Boolean approved){
        StudentCourseId studentCourseId = new StudentCourseId(studentId, courseId);
        StudentCourse studentCourse = studentCourseRepository.findById(studentCourseId).orElseThrow(() -> new RuntimeException("data not found", new NotFoundException()));
        studentCourse.setAprproved(approved);

        return studentCourse;
    }
}
