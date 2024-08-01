package com.example.api_university_manager.components.degree;

public class DegreeDTO {
    private  Long id;
    private String name;
    private Integer numberOfHours;
    private Integer numberOfCourses;

    public DegreeDTO(){}

    public DegreeDTO(Long id, String name, Integer numberOfHours, Integer numberOfCourses) {
        this.id = id;
        this.name = name;
        this.numberOfHours = numberOfHours;
        this.numberOfCourses = numberOfCourses;
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
}
