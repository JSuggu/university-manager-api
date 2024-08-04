package com.example.api_university_manager.components.professor;

import com.example.api_university_manager.components.course.Course;
import com.example.api_university_manager.components.course.CourseDTO;
import com.example.api_university_manager.components.course.CourseRepository;
import com.example.api_university_manager.components.jwt.Token;
import com.example.api_university_manager.components.student_course.StudentCourse;
import com.example.api_university_manager.util.OtherUtilities;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtherUtilities otherUtilities;
    private final ModelMapper modelMapper;

    public ProfessorService(ProfessorRepository professorRepository, CourseRepository courseRepository, PasswordEncoder passwordEncoder, OtherUtilities otherUtilities, ModelMapper modelMapper){
        this.professorRepository = professorRepository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
        this.otherUtilities = otherUtilities;
        this.modelMapper = modelMapper;
    }

    public List<ProfessorDTO> getAllProfessors(){
        List<Professor> allProfessors = professorRepository.findAll();

        return allProfessors
                .stream()
                .map(professor -> modelMapper.map(professor, ProfessorDTO.class))
                .toList();
    }

    @Transactional
    public ProfessorDTO saveProfessor(ProfessorDTO newProfessor){
        Professor professorToSave = new Professor(newProfessor.getNames(), newProfessor.getUsername(), newProfessor.getPassword(), null);
        professorToSave.setPassword(passwordEncoder.encode(newProfessor.getPassword()));

        HashSet<Course> coursesToRegister = new HashSet<>();
        if(newProfessor.getCourseSet() != null && !newProfessor.getCourseSet().isEmpty()){
            for(CourseDTO course: newProfessor.getCourseSet()){
                Course savedCourse = null;

                if(course.getId() != null) savedCourse = courseRepository.findById(course.getId()).orElseThrow(() -> new RuntimeException("Course not found", new NotFoundException()));
                else if(course.getName() != null) savedCourse = courseRepository.findByName(course.getName()).orElseThrow(() -> new RuntimeException("Course not found", new NotFoundException()));

                if(savedCourse != null){
                    coursesToRegister.add(savedCourse);
                }
            }
        }

        professorToSave.setCourseSet(coursesToRegister);

        Professor savedProfessor = professorRepository.save(professorToSave);

        return modelMapper.map(savedProfessor, ProfessorDTO.class);
    }

    public Token login(ProfessorDTO requestData) {
        return otherUtilities.loginProcess(modelMapper.map(requestData, Professor.class));
    }

    @Transactional
    public ProfessorDTO updateProfessor(Long professorId, ProfessorDTO requestData){
        Professor professorToUpdate = professorRepository.findById(professorId).orElseThrow(() -> new RuntimeException("Professor not found", new NotFoundException()));
        professorToUpdate.setNames(requestData.getNames());
        professorToUpdate.setUsername(requestData.getUsername());
        professorToUpdate.setPassword(requestData.getPassword());

        Professor updatedProfessor = professorRepository.save(professorToUpdate);
        return modelMapper.map(updatedProfessor, ProfessorDTO.class);
    }

    @Transactional
    public ProfessorDTO updateCoursesOfProfessor(Long professorId, List<CourseDTO> requestData){
        Professor professorToUpdate = professorRepository.findById(professorId).orElseThrow(() -> new RuntimeException("Professor not found", new NotFoundException()));

        professorToUpdate.setCourseSet(requestData
                .stream()
                .map(courseDTO -> modelMapper.map(courseDTO, Course.class))
                .collect(Collectors.toSet())
        );

        Professor updatedCoursesOfProfessor = professorRepository.save(professorToUpdate);
        return modelMapper.map(updatedCoursesOfProfessor, ProfessorDTO.class);
    }

    public void deleteProfessor(Long idToDelete){
        professorRepository.deleteById(idToDelete);
    }


}
