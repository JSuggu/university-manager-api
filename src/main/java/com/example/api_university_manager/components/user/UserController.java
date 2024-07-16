package com.example.api_university_manager.components.user;

import com.example.api_university_manager.components.professor.Professor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.status(200).body(userList);
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveCourse(@RequestBody User requestData){
        User savedUser = userService.saveUser(requestData);
        return ResponseEntity.status(201).body(savedUser);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateCourse(@PathVariable(name = "id") Long idToUpdate, @RequestBody User requestData) {
        User updatedUser = userService.updateUser(idToUpdate, requestData);
        return ResponseEntity.status(201).body(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") Long idToDelete){
        userService.deleteUser(idToDelete);
        return ResponseEntity.status(200).body("User deleted");
    }
}
