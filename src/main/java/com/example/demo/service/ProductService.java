package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Product;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products = productRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK.value()).body(products);
    }

    public ResponseEntity<Product> findById(UUID id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found by id " + id));
        return ResponseEntity.status(HttpStatus.OK.value()).body(product);
    }

    public ResponseEntity<Product> save(Product product){
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(savedProduct);
    }

    public ResponseEntity<Void> delete(UUID id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found by id " + id));
        productRepository.delete(product);
        return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();
    }
}
