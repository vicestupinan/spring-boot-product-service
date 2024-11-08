package com.example.demo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Category;
import com.example.demo.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Slice<Category>> getAllProducts(@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size,
            @RequestParam(required = false, defaultValue = "id") String sort) {
        return categoryService.findAll(page, size, sort);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getProductById(@PathVariable UUID id) {
        return categoryService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Category> saveProduct(@Valid @RequestBody Category category) {
        return categoryService.save(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateProduct(@Valid @RequestBody Category category, @PathVariable UUID id) {
        return categoryService.update(id, category);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        return categoryService.delete(id);
    }
}
