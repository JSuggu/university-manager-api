package com.example.api_university_manager.components.degree;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

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
        return ResponseEntity.status(200).body(degreesList);
    }

    @PostMapping("/save")
    public ResponseEntity<Degree> saveDegree(@RequestBody Degree requestData){
        Degree savedDegree = degreeService.saveDegree(requestData);
        return ResponseEntity.status(201).body(savedDegree);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Degree> updateDegree(@PathVariable(name = "id") Long idToUpdate, @RequestBody Degree requestData) {
        Degree updatedDegree = degreeService.updateDegree(idToUpdate, requestData);
        return ResponseEntity.status(201).body(updatedDegree);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDegree(@PathVariable("id") Long idToDelete){
        degreeService.deleteDegree(idToDelete);
        return ResponseEntity.status(200).body("Degree deleted");
    }
}
