package com.example.api_university_manager.components.student;

import com.example.api_university_manager.components.degree.Degree;
import com.example.api_university_manager.components.student_course.StudentCourse;
import com.example.api_university_manager.util.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String names;
    private Integer dni;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(
            name = "student_degree",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "degree_id")
    )
    Set<Degree> degreeSet;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<StudentCourse> courseSet;

    public Student(){}

    public Student(String names, Integer dni, String email, String password, Set<Degree> degreeSet, Set<StudentCourse> courseSet) {
        this.names = names;
        this.dni = dni;
        this.username = email;
        this.password = password;
        this.roles = Set.of(Role.STUDENT);
        this.degreeSet = degreeSet;
        this.courseSet = courseSet;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                .toList();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public void setUsername(String email) {
        this.username = email;
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

    public Set<Degree> getDegreeSet() {
        return degreeSet;
    }

    public void setDegreeSet(Set<Degree> degreeSet) {
        this.degreeSet = degreeSet;
    }

    public Set<StudentCourse> getCourseSet() {
        return courseSet;
    }

    public void setCourseSet(Set<StudentCourse> courseSet) {
        this.courseSet = courseSet;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", names='" + names + '\'' +
                ", dni='" + dni + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", degreeSet=" + degreeSet +
                ", courseSet=" + courseSet +
                '}';
    }
}
