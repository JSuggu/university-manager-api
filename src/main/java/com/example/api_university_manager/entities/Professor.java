package com.example.api_university_manager.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="professors")
public class Professor implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String names;
    String username;
    String password;

    @ManyToMany
    @JoinTable(
            name="professor_course",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name="course_id")
    )
    Set<Course> courseSet;

    public Professor(){}

    public Professor(String names, String email, String password, Set<Course> courseSet) {
        this.names = names;
        this.username = email;
        this.password = password;
        this.courseSet = courseSet;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Course> getCourseSet() {
        return courseSet;
    }

    public void setCourseSet(Set<Course> courseSet) {
        this.courseSet = courseSet;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", names='" + names + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", courseSet=" + courseSet +
                '}';
    }
}
