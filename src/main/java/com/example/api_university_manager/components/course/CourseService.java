package com.example.api_university_manager.components.course;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;


    public CourseService(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    public List<CourseDTO> getAllCourses() {
        List<Course> allCourses = courseRepository.findAll();

        return allCourses
                .stream()
                .map(course -> modelMapper.map(course, CourseDTO.class))
                .toList();
    }

    public CourseDTO getCourseById(Long courseId){
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("course not found", new NotFoundException()));
        return modelMapper.map(course, CourseDTO.class);
    }

    public CourseDTO getCourseByName(String courseName){
        Course course = courseRepository.findByName(courseName).orElseThrow(() -> new RuntimeException("course not found", new NotFoundException()));
        return modelMapper.map(course, CourseDTO.class);
    }

    @Transactional
    public CourseDTO saveCourse(CourseDTO requestData) {
        Course savedCourse = courseRepository.save(modelMapper.map(requestData, Course.class));
        return modelMapper.map(savedCourse, CourseDTO.class);
    }

    @Transactional
    public CourseDTO updateCourse(Long idToUpdate, CourseDTO requestData) {
        Course courseToUpdate = courseRepository.findById(idToUpdate).orElseThrow(() -> new RuntimeException("Course not found", new NotFoundException()));
        courseToUpdate.setName(requestData.getName());
        courseToUpdate.setNumberOfHours(requestData.getNumberOfHours());

        Course savedCourse = courseRepository.save(courseToUpdate);

        return modelMapper.map(savedCourse, CourseDTO.class);
    }

    public void deleteCourse(Long idToDelete) {
        courseRepository.deleteById(idToDelete);
    }
}
