package com.example.api_university_manager.components.degree;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/degrees")
public class DegreeController {
    private final DegreeService degreeService;

    public DegreeController(DegreeService degreeService){
        this.degreeService = degreeService;
    }

    @GetMapping("/admin/get/all")
    public ResponseEntity<List<DegreeDTO>> getAllDegrees(){
        List<DegreeDTO> degreesList = degreeService.getAllDegrees();
        return ResponseEntity.status(200).body(degreesList);
    }

    @PostMapping("/admin/save")
    public ResponseEntity<DegreeDTO> saveDegree(@RequestBody DegreeDTO requestData){
        DegreeDTO savedDegree = degreeService.saveDegree(requestData);
        return ResponseEntity.status(201).body(savedDegree);
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<DegreeDTO> updateDegree(@PathVariable(name = "id") Long idToUpdate, @RequestBody DegreeDTO requestData) {
        DegreeDTO updatedDegree = degreeService.updateDegree(idToUpdate, requestData);
        return ResponseEntity.status(201).body(updatedDegree);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<String> deleteDegree(@PathVariable("id") Long idToDelete){
        degreeService.deleteDegree(idToDelete);
        return ResponseEntity.status(200).body("Degree deleted");
    }
}
