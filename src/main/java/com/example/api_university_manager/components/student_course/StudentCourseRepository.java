package com.example.api_university_manager.components.student_course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, StudentCourseId> {

}
