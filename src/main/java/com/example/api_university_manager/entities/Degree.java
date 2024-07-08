package com.example.api_university_manager.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "degrees")
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer numberOfHours;
    private Integer numberOfCourses;
    @ManyToMany(mappedBy = "degreeSet")
    private Set<Student> studentSet;

    public Degree(){}

    public Degree(String name, Integer numberOfHours, Integer numberOfCourses, Set<Student> studentSet) {
        this.name = name;
        this.numberOfHours = numberOfHours;
        this.numberOfCourses = numberOfCourses;
        this.studentSet = studentSet;
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

    public Integer getNumberOfCourses() {
        return numberOfCourses;
    }

    public void setNumberOfCourses(Integer numberOfCourses) {
        this.numberOfCourses = numberOfCourses;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    @Override
    public String toString() {
        return "Degree{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfHours=" + numberOfHours +
                ", numberOfCourses=" + numberOfCourses +
                ", studentSet=" + studentSet +
                '}';
    }
}
