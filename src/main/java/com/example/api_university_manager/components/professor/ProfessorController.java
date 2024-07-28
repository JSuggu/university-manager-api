package com.example.api_university_manager.components.professor;

import com.example.api_university_manager.components.jwt.Token;
import com.example.api_university_manager.components.student.Student;
import com.example.api_university_manager.components.student_course.StudentCourse;
import com.example.api_university_manager.components.student_course.StudentCourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professors")
public class ProfessorController {
    private final ProfessorService professorService;
    private final StudentCourseService studentCourseService;

    public ProfessorController(ProfessorService professorService, StudentCourseService studentCourseService) {
        this.professorService = professorService;
        this.studentCourseService = studentCourseService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Professor>> getAllProfessors(){
        List<Professor> professorList = professorService.getAllProfessor();
        return ResponseEntity.status(200).body(professorList);
    }

    @PostMapping("/save")
    public ResponseEntity<Professor> saveProfessor(@RequestBody Professor requestData){
        Professor savedProfessor = professorService.saveProfessor(requestData);
        return ResponseEntity.status(201).body(savedProfessor);
    }

    @PostMapping("login")
    public ResponseEntity<Token> login(@RequestBody Professor requestData){
        Token jwt = professorService.login(requestData);
        return ResponseEntity.status(200).body(jwt);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable(name = "id") Long idToUpdate, @RequestBody Professor requestData) {
        Professor updatedProfessor = professorService.updateProfessor(idToUpdate, requestData);
        return ResponseEntity.status(201).body(updatedProfessor);
    }

    @PutMapping("/update/{studentId}/{courseId}")
    public ResponseEntity<StudentCourse> updateStudentCourse(@PathVariable(name = "studentId") Long studentId, @PathVariable(name = "courseId") Long courseId, @RequestParam boolean approved) {
        StudentCourse updatedStudentCourse = studentCourseService.updateCourseStateToStudent(studentId,courseId,approved);
        return ResponseEntity.status(201).body(updatedStudentCourse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProfessor(@PathVariable("id") Long idToDelete){
        professorService.deleteProfessor(idToDelete);
        return ResponseEntity.status(200).body("Professor deleted");
    }

}
