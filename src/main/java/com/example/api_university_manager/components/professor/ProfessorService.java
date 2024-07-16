package com.example.api_university_manager.components.professor;

import com.example.api_university_manager.components.course.Course;
import com.example.api_university_manager.components.course.CourseService;
import org.springframework.stereotype.Service;
import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final CourseService courseService;

    public ProfessorService(ProfessorRepository professorRepository, CourseService courseService){
        this.professorRepository = professorRepository;
        this.courseService = courseService;
    }

    public List<Professor> getAllProfessor(){
        return professorRepository.findAll();
    }

    public Professor saveProfessor(Professor requestData){
        return professorRepository.save(requestData);
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
