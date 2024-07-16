package com.example.api_university_manager.components.student_course;

import com.example.api_university_manager.components.course.Course;
import com.example.api_university_manager.components.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class StudentCourse {
    @EmbeddedId
    private StudentCourseId id = new StudentCourseId();
    @JsonIgnore
    @ManyToOne()
    @MapsId("studentId")
    private Student student;

    @ManyToOne()
    @MapsId("courseId")
    private Course course;

    private boolean aprproved;

    public StudentCourse(){}

    public StudentCourse(Student student, Course course, boolean aprproved) {
        this.student = student;
        this.course = course;
        this.aprproved = aprproved;
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

    public boolean isAprproved() {
        return aprproved;
    }

    public void setAprproved(boolean aprproved) {
        this.aprproved = aprproved;
    }
}
