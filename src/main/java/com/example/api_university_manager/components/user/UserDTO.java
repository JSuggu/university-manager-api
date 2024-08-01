package com.example.api_university_manager.components.user;

import com.example.api_university_manager.util.Role;

import java.util.Set;

public class UserDTO {
    private String username;
    private String password;
    private Set<Role> roles;

    public UserDTO(){}

    public UserDTO(String email, String password, Set<Role> roles) {
        this.username = email;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
