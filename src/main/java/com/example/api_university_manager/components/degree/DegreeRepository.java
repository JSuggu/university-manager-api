package com.example.api_university_manager.components.degree;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DegreeRepository extends JpaRepository<Degree, Long> {
    public Degree findByName(String name);
}
