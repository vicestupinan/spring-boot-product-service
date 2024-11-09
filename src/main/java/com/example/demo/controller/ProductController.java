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

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductVariant;
import com.example.demo.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Slice<Product>> getAllProducts(@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size,
            @RequestParam(required = false, defaultValue = "id") String sort) {
        return productService.findAll(page, size, sort);
    }

    @GetMapping("/variants")
    public ResponseEntity<Slice<ProductVariant>> getAllProductVariants(@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size,
            @RequestParam(required = false, defaultValue = "id") String sort) {
        return productService.findAllVariants(page, size, sort);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        return productService.findById(id);
    }

    @GetMapping("/variants/{id}")
    public ResponseEntity<ProductVariant> getProductVariantById(@PathVariable UUID id) {
        return productService.findVariantById(id);
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product) {
        return productService.save(product);
    }

    @PostMapping("/variants/{id}")
    public ResponseEntity<ProductVariant> saveProductVariant(@Valid @RequestBody ProductVariant productVariant, @PathVariable UUID id) {
        return productService.saveVariant(id, productVariant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product, @PathVariable UUID id) {
        return productService.update(id, product);
    }

    @PutMapping("/variants/{id}")
    public ResponseEntity<ProductVariant> updateProductVariant(@Valid @RequestBody ProductVariant productVariant, @PathVariable UUID id) {
        return productService.updateVariant(id, productVariant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        return productService.delete(id);
    }

    @DeleteMapping("/variants/{id}")
    public ResponseEntity<Void> deleteProductVariant(@PathVariable UUID id) {
        return productService.deleteVariant(id);
    }
}
