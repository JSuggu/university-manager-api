package com.example.api_university_manager.components.student;

import com.example.api_university_manager.components.degree.DegreeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.status(200).body(degreesList);
    }

    @PostMapping("/save")
    public ResponseEntity<Student> saveDegree(@RequestBody Student requestData){
        Student savedStudent = studentService.saveStudent(requestData);
        return ResponseEntity.status(201).body(savedStudent);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(name = "id") Long idToUpdate,@RequestBody Student requestData){
        Student updatedStudent = studentService.updateStudent(idToUpdate, requestData);
        return ResponseEntity.status(201).body(updatedStudent);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long idToDelete){
        studentService.deleteStudent(idToDelete);
        return ResponseEntity.status(200).body("Degree deleted");
    }
}
