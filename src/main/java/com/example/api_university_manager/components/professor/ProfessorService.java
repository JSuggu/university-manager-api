package com.example.api_university_manager.components.professor;

import com.example.api_university_manager.components.course.Course;
import com.example.api_university_manager.components.course.CourseDTO;
import com.example.api_university_manager.components.course.CourseService;
import com.example.api_university_manager.components.jwt.Token;
import com.example.api_university_manager.util.OtherUtilities;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtherUtilities otherUtilities;
    private final ModelMapper modelMapper;

    public ProfessorService(ProfessorRepository professorRepository, PasswordEncoder passwordEncoder, OtherUtilities otherUtilities, ModelMapper modelMapper){
        this.professorRepository = professorRepository;
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
        newProfessor.setPassword(passwordEncoder.encode(newProfessor.getPassword()));
        Professor savedProfessor = professorRepository.save(modelMapper.map(newProfessor, Professor.class));

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
