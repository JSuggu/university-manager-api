package com.example.api_university_manager.components.degree;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DegreeService {
    private final DegreeRepository degreeRepository;

    public DegreeService(DegreeRepository degreeRepository){
        this.degreeRepository = degreeRepository;
    }

    public List<Degree> getAllDegrees(){
        return degreeRepository.findAll();
    }

    public Degree saveDegree(Degree newDegree){
        return degreeRepository.save(newDegree);
    }

    public Degree updateDegree(Long id, Degree updatedDegree){
        Degree degreeForUpdate = degreeRepository.findById(id).orElseThrow(()-> new RuntimeException("Carrera no encontrado"));
        if (updatedDegree.getName() != null) degreeForUpdate.setName(updatedDegree.getName());
        if (updatedDegree.getNumberOfHours() != null) degreeForUpdate.setNumberOfHours(updatedDegree.getNumberOfHours());
        if (updatedDegree.getNumberOfCourses() != null) degreeForUpdate.setNumberOfCourses(updatedDegree.getNumberOfCourses());
        return degreeRepository.save(degreeForUpdate);
    }

    public void deleteDegree(Long id){
        degreeRepository.deleteById(id);
    }

}
