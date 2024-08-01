package com.example.api_university_manager.components.course;

import java.util.Set;

public class CourseDTO {
    private Long id;
    private String name;
    private Integer numberOfHours;

    public CourseDTO() {
    }

    public CourseDTO(Long id, String name, Integer numberOfHours) {
        this.id = id;
        this.name = name;
        this.numberOfHours = numberOfHours;
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
}
