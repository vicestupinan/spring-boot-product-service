package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    
}
