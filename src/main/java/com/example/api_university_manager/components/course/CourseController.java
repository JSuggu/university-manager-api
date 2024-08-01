package com.example.api_university_manager.components.course;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        List<CourseDTO> coursesList = courseService.getAllCourses();
        return ResponseEntity.status(200).body(coursesList);
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<CourseDTO> getCourseByName(@PathVariable(name = "name") String courseName){
        CourseDTO course = courseService.getCourseByName(courseName);
        return ResponseEntity.status(200).body(course);
    }

    @PostMapping("/save")
    public ResponseEntity<CourseDTO> saveCourse(@RequestBody CourseDTO requestData){
        CourseDTO savedCourse = courseService.saveCourse(requestData);
        return ResponseEntity.status(201).body(savedCourse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable(name = "id") Long idToUpdate, @RequestBody CourseDTO requestData) {
        CourseDTO updatedCourse = courseService.updateCourse(idToUpdate, requestData);
        return ResponseEntity.status(201).body(updatedCourse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") Long idToDelete){
        courseService.deleteCourse(idToDelete);
        return ResponseEntity.status(200).body("Course deleted");
    }

}
