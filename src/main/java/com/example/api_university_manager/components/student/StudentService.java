package com.example.api_university_manager.components.student;

import com.example.api_university_manager.components.degree.Degree;
import com.example.api_university_manager.components.degree.DegreeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final DegreeRepository degreeRepository;


    public StudentService(StudentRepository studentRepository, DegreeRepository degreeRepository){
        this.studentRepository = studentRepository;
        this.degreeRepository = degreeRepository;
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @Transactional
    public Student saveDegree(Student newStudent){
        Set<Degree> degreesToRegister = new HashSet<>();
        for(Degree degree: newStudent.degreeSet){
            Degree degreeSaved = degreeRepository.findByName(degree.getName());
            if(degreeSaved != null) degreesToRegister.add(degreeSaved);
        }

        newStudent.setDegreeSet(degreesToRegister);
        return studentRepository.save(newStudent);
    }

    @Transactional
    public Student updateDegree(Long id, Student updatedStudent){
        Student studentToUpdate = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        if(updatedStudent.getNames() != null) studentToUpdate.setNames(updatedStudent.getNames());
        if(updatedStudent.getDni() != null) studentToUpdate.setDni(updatedStudent.getDni());
        if(updatedStudent.getUsername() != null) studentToUpdate.setUsername(updatedStudent.getUsername());
        if(updatedStudent.getPassword() != null) studentToUpdate.setPassword(updatedStudent.getPassword());
        if(!updatedStudent.getDegreeSet().isEmpty()) studentToUpdate.setDegreeSet(updatedStudent.getDegreeSet());
        if(!updatedStudent.getCourseSet().isEmpty()) studentToUpdate.setCourseSet(updatedStudent.getCourseSet());

        studentToUpdate.setUsername(updatedStudent.getUsername());

        return studentToUpdate;
    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }
}
