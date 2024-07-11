package com.example.api_university_manager.components.student;

import com.example.api_university_manager.components.degree.Degree;
import com.example.api_university_manager.components.degree.DegreeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService, DegreeService degreeService){
        this.studentService = studentService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> degreesList = studentService.getAllStudents();
        return ResponseEntity.ok(degreesList);
    }

    @PostMapping("/save")
    public ResponseEntity<Student> saveDegree(@RequestBody Student requestData){
        Student savedStudent = studentService.saveDegree(requestData);
        return ResponseEntity.ok(savedStudent);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long idToUpdate,@RequestBody Student requestData){
        Student updatedStudent = studentService.updateDegree(idToUpdate, requestData);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long idToDelete){
        studentService.deleteStudent(idToDelete);
        return ResponseEntity.ok("Degree deleted");
    }
}
