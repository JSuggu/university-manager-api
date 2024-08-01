package com.example.api_university_manager.components.student_course;

import com.example.api_university_manager.components.course.Course;
import com.example.api_university_manager.components.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class StudentCourse {
    @EmbeddedId
    private StudentCourseId id = new StudentCourseId();

    @ManyToOne()
    @MapsId("studentId")
    private Student student;

    @ManyToOne()
    @MapsId("courseId")
    private Course course;

    private Boolean approved;

    public StudentCourse(){}

    public StudentCourse(Student student, Course course, Boolean approved) {
        this.student = student;
        this.course = course;
        this.approved = approved;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Boolean isApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
