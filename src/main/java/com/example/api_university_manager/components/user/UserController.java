package com.example.api_university_manager.components.user;

import com.example.api_university_manager.components.jwt.Token;
import com.example.api_university_manager.components.professor.Professor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dev/get/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> userList = userService.getAllUsers();
        return ResponseEntity.status(200).body(userList);
    }

    @PostMapping("/dev/save")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO requestData){
        UserDTO savedUser = userService.saveUser(requestData);
        return ResponseEntity.status(201).body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody UserDTO requestData){
        Token jwt = userService.login(requestData);
        return ResponseEntity.status(200).body(jwt);
    }

    @PutMapping("/dev/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(name = "id") Long idToUpdate, @RequestBody UserDTO requestData) {
        UserDTO updatedUser = userService.updateUser(idToUpdate, requestData);
        return ResponseEntity.status(201).body(updatedUser);
    }

    @DeleteMapping("/dev/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long idToDelete){
        userService.deleteUser(idToDelete);
        return ResponseEntity.status(200).body("User deleted");
    }
}
