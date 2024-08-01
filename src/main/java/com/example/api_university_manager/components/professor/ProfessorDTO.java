package com.example.api_university_manager.components.professor;

import com.example.api_university_manager.components.course.CourseDTO;

import java.util.Set;

public class ProfessorDTO {
    private Long id;
    private String names;
    private String username;
    private String password;
    private Set<CourseDTO> courseSet;

    public ProfessorDTO(){}

    public ProfessorDTO(Long id, String names, String username, String password, Set<CourseDTO> courseSet) {
        this.id = id;
        this.names = names;
        this.username = username;
        this.password = password;
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

    public Set<CourseDTO> getCourseSet() {
        return courseSet;
    }

    public void setCourseSet(Set<CourseDTO> courseSet) {
        this.courseSet = courseSet;
    }
}
