package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.ProductVariant;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, UUID> {
    
}
