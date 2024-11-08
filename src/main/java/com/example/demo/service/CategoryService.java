package com.example.demo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Category;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<Slice<Category>> findAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Slice<Category> categories = categoryRepository.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK.value()).body(categories);
    }

    public ResponseEntity<Category> findById(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found by id " + id));
        return ResponseEntity.status(HttpStatus.OK.value()).body(category);
    }

    public ResponseEntity<Category> save(Category category) {
        Category savedCategory = categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(savedCategory);
    }

    public ResponseEntity<Category> update(UUID id, Category category) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found by id " + id));
        existingCategory.setName(category.getName());
        return ResponseEntity.status(HttpStatus.OK.value()).body(existingCategory);
    }

    public ResponseEntity<Void> delete(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found by id " + id));
        categoryRepository.delete(category);
        return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();
    }
}
