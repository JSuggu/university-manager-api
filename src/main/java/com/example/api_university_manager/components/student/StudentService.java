package com.example.api_university_manager.components.student;

import com.example.api_university_manager.components.course.Course;
import com.example.api_university_manager.components.course.CourseService;
import com.example.api_university_manager.components.degree.Degree;
import com.example.api_university_manager.components.degree.DegreeService;
import com.example.api_university_manager.components.jwt.Token;
import com.example.api_university_manager.components.student_course.StudentCourse;
import com.example.api_university_manager.util.OtherUtilities;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final DegreeService degreeService;
    private final CourseService courseService;
    private final PasswordEncoder passwordEncoder;
    private final OtherUtilities otherUtilities;


    public StudentService(StudentRepository studentRepository, DegreeService degreeService, CourseService courseService, PasswordEncoder passwordEncoder, OtherUtilities otherUtilities){
        this.studentRepository = studentRepository;
        this.degreeService = degreeService;
        this.courseService = courseService;
        this.passwordEncoder = passwordEncoder;
        this.otherUtilities = otherUtilities;
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @Transactional
    public Student saveStudent(Student newStudent){
        Set<Degree> degreesToRegister = new HashSet<>();
        for(Degree degree: newStudent.getDegreeSet()){
            Degree degreeSaved = degreeService.getDegreeByName(degree.getName());
            if(degreeSaved != null) degreesToRegister.add(degreeSaved);
        }

        Set<StudentCourse> courseToRegister = new HashSet<>();
        StudentCourse newStudentCourse;
        for(StudentCourse course: newStudent.getCourseSet()){
            Course courseSaved = courseService.getCourseByName(course.getCourse().getName());
            if(courseSaved != null){
                newStudentCourse = new StudentCourse(newStudent, courseSaved, false);
                courseToRegister.add(newStudentCourse);
            }
        }

        newStudent.setPassword(passwordEncoder.encode(newStudent.getPassword()));

        newStudent.setDegreeSet(degreesToRegister);
        newStudent.setCourseSet(courseToRegister);
        return studentRepository.save(newStudent);
    }

    public Token login(Student requestData) {
        return otherUtilities.loginProcess(requestData);
    }

    @Transactional
    public Student updateStudent(Long id, Student updatedStudent) {
        Student studentToUpdate = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found", new NotFoundException()));

        studentToUpdate.setNames(updatedStudent.getNames());
        studentToUpdate.setDni(updatedStudent.getDni());
        studentToUpdate.setUsername(updatedStudent.getUsername());
        studentToUpdate.setPassword(updatedStudent.getPassword());

        Set<Degree> degreesToRegister = new HashSet<>();
        for(Degree degree: updatedStudent.degreeSet){
            Degree degreeSaved = degreeService.getDegreeByName(degree.getName());
            if(degreeSaved != null) degreesToRegister.add(degreeSaved);
        }

        Set<StudentCourse> coursesToRegister = new HashSet<>();
        StudentCourse newStudentCourse;
        for(StudentCourse studentCourse: updatedStudent.courseSet){
            Course courseSaved = courseService.getCourseByName(studentCourse.getCourse().getName());
            if(courseSaved != null){
                newStudentCourse = new StudentCourse(updatedStudent, courseSaved, false);
                coursesToRegister.add(newStudentCourse);
            }
        }

        studentToUpdate.setDegreeSet(degreesToRegister);
        studentToUpdate.setCourseSet(coursesToRegister);

        return studentRepository.save(studentToUpdate);
    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }
}
