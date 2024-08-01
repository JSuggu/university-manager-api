package com.example.api_university_manager.components.user;

import com.example.api_university_manager.components.jwt.Token;
import com.example.api_university_manager.util.OtherUtilities;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtherUtilities otherUtilities;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, OtherUtilities otherUtilities, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.otherUtilities = otherUtilities;
        this.modelMapper = modelMapper;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }

    public UserDTO saveUser(UserDTO newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User savedUser = userRepository.save(modelMapper.map(newUser, User.class));
        return modelMapper.map(savedUser, UserDTO.class);
    }

    public Token login(UserDTO requestData){
        return otherUtilities.loginProcess(modelMapper.map(requestData, User.class));
    }

    public UserDTO updateUser(Long idToUpdate, UserDTO requestData) {
        User userToUpdate = userRepository.findById(idToUpdate).orElseThrow(() -> new RuntimeException("User not found", new NotFoundException()));
        userToUpdate.setUsername(requestData.getUsername());
        userToUpdate.setPassword(requestData.getPassword());

        return modelMapper.map(userToUpdate, UserDTO.class);
    }

    public void deleteUser(Long idToDelete) {
        userRepository.deleteById(idToDelete);
    }
}
