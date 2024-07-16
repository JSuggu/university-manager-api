package com.example.api_university_manager.components.student_course;

import com.example.api_university_manager.components.course.Course;
import com.example.api_university_manager.components.student.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class Student_Course {
    private Student_Course_Id id = 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId")
    private Course course;

    private boolean aprproved;

    public Student_Course(){}

    public Student_Course(Student student, Course course, boolean aprproved) {
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
