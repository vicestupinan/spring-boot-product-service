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

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductVariant;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ProductVariantRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductVariantRepository productVariantRepository;

    public ResponseEntity<Slice<Product>> findAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Slice<Product> products = productRepository.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK.value()).body(products);
    }

    public ResponseEntity<Slice<ProductVariant>> findAllVariants(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Slice<ProductVariant> productVariants = productVariantRepository.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK.value()).body(productVariants);
    }

    public ResponseEntity<Product> findById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found by id " + id));
        return ResponseEntity.status(HttpStatus.OK.value()).body(product);
    }

    public ResponseEntity<ProductVariant> findVariantById(UUID id) {
        ProductVariant productVariant = productVariantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Variant Not Found by id " + id));
        return ResponseEntity.status(HttpStatus.OK.value()).body(productVariant);
    }

    public ResponseEntity<Product> save(Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(savedProduct);
    }

    public ResponseEntity<ProductVariant> saveVariant(UUID id, ProductVariant productVariant) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found by id " + id));
        productVariant.setProduct(existingProduct);
        ProductVariant savedProductVariant = productVariantRepository.save(productVariant);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(savedProductVariant);
    }

    public ResponseEntity<Product> update(UUID id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found by id " + id));
        existingProduct.setName(product.getName());
        existingProduct.setCategory(product.getCategory());
        productRepository.save(existingProduct);
        return ResponseEntity.status(HttpStatus.OK.value()).body(existingProduct);
    }

    public ResponseEntity<ProductVariant> updateVariant(UUID id, ProductVariant productVariant) {
        ProductVariant existingProductVariant = productVariantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Variant Not Found by id " + id));
        existingProductVariant.setColor(productVariant.getColor());
        existingProductVariant.setSize(productVariant.getSize());
        existingProductVariant.setStock(productVariant.getStock());
        existingProductVariant.setPrice(productVariant.getPrice());
        productVariantRepository.save(existingProductVariant);
        return ResponseEntity.status(HttpStatus.OK.value()).body(existingProductVariant);
    }

    public ResponseEntity<Void> delete(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found by id " + id));
        productRepository.delete(product);
        return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();
    }

    public ResponseEntity<Void> deleteVariant(UUID id) {
        ProductVariant productVariant = productVariantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Variant Not Found by id " + id));
        productVariantRepository.delete(productVariant);
        return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();
    }
}
