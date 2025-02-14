package com.example.api_university_manager.util;

import com.example.api_university_manager.components.course.Course;
import com.example.api_university_manager.components.course.CourseDTO;
import com.example.api_university_manager.components.jwt.JwtService;
import com.example.api_university_manager.components.jwt.Token;
import com.example.api_university_manager.components.student.StudentDTO;
import com.example.api_university_manager.components.student_course.StudentCourse;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OtherUtilities {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public OtherUtilities(JwtService jwtService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    public Token loginProcess(UserDetails user){
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.getUsername());
        String jwt = this.jwtService.generateToken(userDetails);
        return new Token(jwt);
    }
}
