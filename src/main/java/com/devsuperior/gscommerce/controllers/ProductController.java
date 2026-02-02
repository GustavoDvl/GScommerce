package com.devsuperior.gscommerce.controllers;


import com.devsuperior.gscommerce.entities.dto.ProductRequestDTO;
import com.devsuperior.gscommerce.entities.dto.ProductResponseDTO;
import com.devsuperior.gscommerce.service.ProductService;
import io.micrometer.observation.ObservationHandler;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;


public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> insert (@RequestBody ProductRequestDTO requestDTO){
        ProductResponseDTO response = productService.insert(requestDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable @Valid Long id, @RequestBody ProductRequestDTO requestDTO){
        return ResponseEntity.ok(productService.update(id, requestDTO));
        
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
