package com.example.api_university_manager.components.student;

import com.example.api_university_manager.components.course.CourseDTO;
import com.example.api_university_manager.components.degree.DegreeDTO;
import com.example.api_university_manager.util.Role;

import java.util.Set;

public class StudentDTO {
    private Long id;
    private String names;
    private Integer dni;
    private String username;
    private String password;
    private Set<Role> roles;
    private Set<DegreeDTO> degreeSet;
    private Set<CourseDTO> courseSet;


    public StudentDTO(){}

    public StudentDTO(Long id, String names, Integer dni, String username, String password, Set<Role> roles, Set<DegreeDTO> degreeSet, Set<CourseDTO> courseSet) {
        this.id = id;
        this.names = names;
        this.dni = dni;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.degreeSet = degreeSet;
        this.courseSet = courseSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<DegreeDTO> getDegreeSet() {
        return degreeSet;
    }

    public void setDegreeSet(Set<DegreeDTO> degreeSet) {
        this.degreeSet = degreeSet;
    }

    public Set<CourseDTO> getCourseSet() {
        return courseSet;
    }

    public void setCourseSet(Set<CourseDTO> courseSet) {
        this.courseSet = courseSet;
    }
}
