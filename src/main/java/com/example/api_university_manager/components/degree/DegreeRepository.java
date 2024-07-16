package com.example.api_university_manager.components.degree;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DegreeRepository extends JpaRepository<Degree, Long> {
    public Optional<Degree> findByName(String name);
}
