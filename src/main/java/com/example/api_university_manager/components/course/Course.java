package com.example.api_university_manager.components.course;

import com.example.api_university_manager.components.professor.Professor;
import com.example.api_university_manager.components.student_course.StudentCourse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer numberOfHours;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentCourse> studentSet;

    @ManyToMany(mappedBy = "courseSet")
    private Set<Professor> professorSet;

    public Course() {}

    public Course(String name, Integer numberOfHours, Set<StudentCourse> studentSet, Set<Professor> professorSet) {
        this.name = name;
        this.numberOfHours = numberOfHours;
        this.studentSet = studentSet;
        this.professorSet = professorSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(Integer numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public Set<StudentCourse> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<StudentCourse> studentSet) {
        this.studentSet = studentSet;
    }

    public Set<Professor> getProfessorSet() {
        return professorSet;
    }

    public void setProfessorSet(Set<Professor> professorSet) {
        this.professorSet = professorSet;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentSet=" + studentSet +
                ", professorSet=" + professorSet +
                '}';
    }
}
