package com.example.api_university_manager.components.professor;

import com.example.api_university_manager.components.course.Course;
import com.example.api_university_manager.components.course.CourseService;
import com.example.api_university_manager.components.jwt.Token;
import com.example.api_university_manager.util.OtherUtilities;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final CourseService courseService;
    private final PasswordEncoder passwordEncoder;
    private final OtherUtilities otherUtilities;

    public ProfessorService(ProfessorRepository professorRepository, CourseService courseService, PasswordEncoder passwordEncoder, OtherUtilities otherUtilities){
        this.professorRepository = professorRepository;
        this.courseService = courseService;
        this.passwordEncoder = passwordEncoder;
        this.otherUtilities = otherUtilities;
    }

    public List<Professor> getAllProfessor(){
        return professorRepository.findAll();
    }

    public Professor saveProfessor(Professor newProfessor){
        newProfessor.setPassword(passwordEncoder.encode(newProfessor.getPassword()));
        return professorRepository.save(newProfessor);
    }

    public Token login(Professor requestData) {
        return otherUtilities.loginProcess(requestData);
    }

    public Professor updateProfessor(Long idToUpdate, Professor requestData){
        Professor professorToUpdate = professorRepository.findById(idToUpdate).orElseThrow(() -> new RuntimeException("Professor not found", new NotFoundException()));
        professorToUpdate.setNames(requestData.getNames());
        professorToUpdate.setUsername(requestData.getUsername());
        professorToUpdate.setPassword(requestData.getPassword());

        Set<Course> courseToRegister = new HashSet<>();
        for(Course course: requestData.getCourseSet()){
            if(course != null) courseToRegister.add(courseService.getCourseByName(course.getName()));
        }

        professorToUpdate.setCourseSet(courseToRegister);

        return professorToUpdate;
    }

    public void deleteProfessor(Long idToDelete){
        professorRepository.deleteById(idToDelete);
    }
}
