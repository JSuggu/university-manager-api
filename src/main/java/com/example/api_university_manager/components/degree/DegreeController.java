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

    @GetMapping("/get-all")
    public ResponseEntity<List<Degree>> getAllDegrees(){
        List<Degree> degreesList = degreeService.getAllDegrees();
        return ResponseEntity.ok(degreesList);
    }

    @PostMapping("/save")
    public ResponseEntity<Degree> saveDegree(@RequestBody Degree requestData){
        Degree savedDegree = degreeService.saveDegree(requestData);
        return ResponseEntity.ok(savedDegree);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Degree> updateDegree(@PathVariable("id") Long idToUpdate, @RequestBody Degree requestData){
        Degree updatedDegree = degreeService.updateDegree(idToUpdate, requestData);
        return ResponseEntity.ok(updatedDegree);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDegree(@PathVariable("id") Long idToDelete){
        degreeService.deleteDegree(idToDelete);
        return ResponseEntity.ok("Degree deleted");
    }
}
