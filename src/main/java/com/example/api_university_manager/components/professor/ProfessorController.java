package com.example.api_university_manager.components.professor;

import com.example.api_university_manager.components.course.CourseDTO;
import com.example.api_university_manager.components.jwt.Token;
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

    @GetMapping("/get/all")
    public ResponseEntity<List<ProfessorDTO>> getAllProfessors(){
        List<ProfessorDTO> professorList = professorService.getAllProfessors();
        return ResponseEntity.status(200).body(professorList);
    }

    @PostMapping("/save")
    public ResponseEntity<ProfessorDTO> saveProfessor(@RequestBody ProfessorDTO requestData){
        ProfessorDTO savedProfessor = professorService.saveProfessor(requestData);
        return ResponseEntity.status(201).body(savedProfessor);
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody ProfessorDTO requestData){
        Token jwt = professorService.login(requestData);
        return ResponseEntity.status(200).body(jwt);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProfessorDTO> updateProfessor(@PathVariable(name = "id") Long professorId, @RequestBody ProfessorDTO requestData) {
        ProfessorDTO updatedProfessor = professorService.updateProfessor(professorId, requestData);
        return ResponseEntity.status(201).body(updatedProfessor);
    }

    @PutMapping("/update/professor-courses/{professorId}")
    public ResponseEntity<ProfessorDTO> updateCoursesOfProfessor(@PathVariable(name="professorId") Long professorId, @RequestBody List<CourseDTO> requestData){
        ProfessorDTO updatedCoursesOfProfessor = professorService.updateCoursesOfProfessor(professorId, requestData);
        return ResponseEntity.status(201).body(updatedCoursesOfProfessor);
    }

    @PutMapping("/update/course-state/{studentId}/{courseId}/{courseState}")
    public ResponseEntity<StudentCourse> updateCourseState(@PathVariable(name = "studentId") Long studentId, @PathVariable(name = "courseId")
    Long courseId, @PathVariable(name = "courseState") boolean approved) {
        StudentCourse updatedStudentCourse = studentCourseService.updateCourseStateToStudent(studentId,courseId,approved);
        return ResponseEntity.status(201).body(updatedStudentCourse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProfessor(@PathVariable("id") Long idToDelete){
        professorService.deleteProfessor(idToDelete);
        return ResponseEntity.status(200).body("Professor deleted");
    }
}
