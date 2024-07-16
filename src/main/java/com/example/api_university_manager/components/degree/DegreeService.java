package com.example.api_university_manager.components.degree;

import org.springframework.stereotype.Service;
import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.List;

@Service
public class DegreeService {
    private final DegreeRepository degreeRepository;

    public DegreeService(DegreeRepository degreeRepository){
        this.degreeRepository = degreeRepository;
    }

    public List<Degree> getAllDegrees(){
        return degreeRepository.findAll();
    }

    public Degree getDegreeById(Long degreeId) {
        return degreeRepository.findById(degreeId).orElseThrow(() -> new RuntimeException("not found degree", new NotFoundException()));
    }

    public Degree getDegreeByName(String degreeName) {
        return degreeRepository.findByName(degreeName).orElseThrow(() -> new RuntimeException("not found degree", new NotFoundException()));
    }

    public Degree saveDegree(Degree newDegree){
        return degreeRepository.save(newDegree);
    }

    public Degree updateDegree(Long id, Degree updatedDegree) {
        Degree degreeForUpdate = degreeRepository.findById(id).orElseThrow(()-> new RuntimeException("Degree not found", new NotFoundException()));
        degreeForUpdate.setName(updatedDegree.getName());
        degreeForUpdate.setNumberOfHours(updatedDegree.getNumberOfHours());
        degreeForUpdate.setNumberOfCourses(updatedDegree.getNumberOfCourses());
        return degreeRepository.save(degreeForUpdate);
    }

    public void deleteDegree(Long id){
        degreeRepository.deleteById(id);
    }

}
