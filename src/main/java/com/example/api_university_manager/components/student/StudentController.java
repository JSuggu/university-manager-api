package com.example.api_university_manager.components.student;

import com.example.api_university_manager.components.course.CourseDTO;
import com.example.api_university_manager.components.degree.DegreeDTO;
import com.example.api_university_manager.components.degree.DegreeService;
import com.example.api_university_manager.components.jwt.Token;
import com.example.api_university_manager.components.professor.ProfessorDTO;
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

    @GetMapping("/admin/get/all")
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        List<StudentDTO> degreesList = studentService.getAllStudents();
        return ResponseEntity.status(200).body(degreesList);
    }

    @PostMapping("/admin/save")
    public ResponseEntity<StudentDTO> saveDegree(@RequestBody StudentDTO requestData){
        StudentDTO savedStudent = studentService.saveStudent(requestData);
        return ResponseEntity.status(201).body(savedStudent);
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody StudentDTO requestData){
        Token jwt = studentService.login(requestData);
        return ResponseEntity.status(200).body(jwt);
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable(name = "id") Long studentId,@RequestBody StudentDTO requestData){
        StudentDTO updatedStudent = studentService.updateStudent(studentId, requestData);
        return ResponseEntity.status(201).body(updatedStudent);
    }

    @PutMapping("/admin/update/student-degrees/{studentId}")
    public ResponseEntity<StudentDTO> updateDegreesOfStudent(@PathVariable(name="studentId") Long studentId, @RequestBody List<DegreeDTO> requestData){
        StudentDTO updatedCoursesOfStudent = studentService.updateDegreesOfStudent(studentId, requestData);
        return ResponseEntity.status(201).body(updatedCoursesOfStudent);
    }

    @PutMapping("/admin/update/student-courses/{studentId}")
    public ResponseEntity<StudentDTO> updateCoursesOfStudent(@PathVariable(name="studentId") Long studentId, @RequestBody List<CourseDTO> requestData){
        StudentDTO updatedCoursesOfStudent = studentService.updateCoursesOfStudent(studentId, requestData);
        return ResponseEntity.status(201).body(updatedCoursesOfStudent);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long idToDelete){
        studentService.deleteStudent(idToDelete);
        return ResponseEntity.status(200).body("Degree deleted");
    }
}
