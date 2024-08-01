package com.example.api_university_manager.components.student_course;

import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseService {
    private final StudentCourseRepository studentCourseRepository;

    public StudentCourseService(StudentCourseRepository studentCourseRepository) {
        this.studentCourseRepository = studentCourseRepository;
    }

    @Transactional
    public StudentCourse updateCourseStateToStudent(Long studentId, Long courseId, Boolean approved){
        StudentCourseId studentCourseId = new StudentCourseId(studentId, courseId);
        StudentCourse studentCourse = studentCourseRepository.findById(studentCourseId).orElseThrow(() -> new RuntimeException("data not found", new NotFoundException()));
        studentCourse.setApproved(approved);

        return studentCourse;
    }
}
