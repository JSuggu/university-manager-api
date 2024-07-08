package com.example.api_university_manager.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean approved;
    @ManyToMany(mappedBy = "courseSet")
    private Set<Student> studentSet;
    @ManyToMany(mappedBy = "courseSet")
    private Set<Professor> professorSet;

    public Course() {}

    public Course(String name, Boolean approved, Set<Student> studentSet, Set<Professor> professorSet) {
        this.name = name;
        this.approved = approved;
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

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
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
                ", approved=" + approved +
                ", studentSet=" + studentSet +
                ", professorSet=" + professorSet +
                '}';
    }
}
