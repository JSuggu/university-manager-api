package com.example.api_university_manager.components.degree;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.List;

@Service
public class DegreeService {
    private final DegreeRepository degreeRepository;
    private final ModelMapper modelMapper;

    public DegreeService(DegreeRepository degreeRepository, ModelMapper modelMapper){
        this.degreeRepository = degreeRepository;
        this.modelMapper = modelMapper;
    }

    public List<DegreeDTO> getAllDegrees(){
        List<Degree> allDegrees = degreeRepository.findAll();

        return allDegrees
                .stream()
                .map(degree -> modelMapper.map(degree, DegreeDTO.class))
                .toList();
    }

    public DegreeDTO getDegreeById(Long degreeId) {
        Degree degree = degreeRepository.findById(degreeId).orElseThrow(() -> new RuntimeException("not found degree", new NotFoundException()));
        return modelMapper.map(degree, DegreeDTO.class);
    }

    public DegreeDTO getDegreeByName(String degreeName) {
        Degree degree = degreeRepository.findByName(degreeName).orElseThrow(() -> new RuntimeException("not found degree", new NotFoundException()));
        return modelMapper.map(degree, DegreeDTO.class);
    }

    @Transactional
    public DegreeDTO saveDegree(DegreeDTO newDegree){
        Degree savedDegree = degreeRepository.save(modelMapper.map(newDegree, Degree.class));

        return modelMapper.map(savedDegree, DegreeDTO.class);
    }

    @Transactional
    public DegreeDTO updateDegree(Long id, DegreeDTO updatedDegree) {
        Degree degreeForUpdate = degreeRepository.findById(id).orElseThrow(()-> new RuntimeException("Degree not found", new NotFoundException()));

        degreeForUpdate.setName(updatedDegree.getName());
        degreeForUpdate.setNumberOfHours(updatedDegree.getNumberOfHours());
        degreeForUpdate.setNumberOfCourses(updatedDegree.getNumberOfCourses());

        Degree savedCourse = degreeRepository.save(degreeForUpdate);

        return modelMapper.map(savedCourse, DegreeDTO.class);
    }

    public void deleteDegree(Long id){
        degreeRepository.deleteById(id);
    }

}
