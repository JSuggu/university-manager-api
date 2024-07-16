package com.example.api_university_manager.components.user;

import org.springframework.stereotype.Service;
import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User requestData) {
        return userRepository.save(requestData);
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
