package com.example.api_university_manager.components.user;

import com.example.api_university_manager.components.jwt.Token;
import com.example.api_university_manager.util.OtherUtilities;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtherUtilities otherUtilities;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, OtherUtilities otherUtilities) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.otherUtilities = otherUtilities;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    public Token login(User requestData){
        return otherUtilities.loginProcess(requestData);
    }

    public User updateUser(Long idToUpdate, User requestData) {
        User userToUpdate = userRepository.findById(idToUpdate).orElseThrow(() -> new RuntimeException("User not found", new NotFoundException()));
        userToUpdate.setUsername(requestData.getUsername());
        userToUpdate.setPassword(requestData.getPassword());

        return userToUpdate;
    }

    public void deleteUser(Long idToDelete) {
        userRepository.deleteById(idToDelete);
    }
}
