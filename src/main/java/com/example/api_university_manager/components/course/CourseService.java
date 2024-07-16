package com.example.api_university_manager.components.course;

import org.springframework.stereotype.Service;
import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseByName(String courseName){
        return courseRepository.findByName(courseName).orElseThrow(() -> new RuntimeException("course not found", new NotFoundException()));
    }

    public Course getCourseById (Long courseId){
        return courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("course not found", new NotFoundException()));
    }

    public Course saveCourse(Course requestData) {
        return courseRepository.save(requestData);
    }

    public Course updateCourse(Long idToUpdate, Course requestData) {
        Course courseToUpdate = courseRepository.findById(idToUpdate).orElseThrow(() -> new RuntimeException("Course not found", new NotFoundException()));
        courseToUpdate.setName(requestData.getName());
        courseToUpdate.setNumberOfHours(requestData.getNumberOfHours());

        return courseRepository.save(courseToUpdate);
    }

    public void deleteCourse(Long idToDelete) {
        courseRepository.deleteById(idToDelete);
    }
}
