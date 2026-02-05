package com.devsuperior.gscommerce.controllers;


import com.devsuperior.gscommerce.dto.ProductRequestDTO;
import com.devsuperior.gscommerce.dto.ProductResponseDTO;
import com.devsuperior.gscommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> insert (@Valid @RequestBody ProductRequestDTO requestDTO){
        ProductResponseDTO response = productService.insert(requestDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO requestDTO){
        return ResponseEntity.ok(productService.update(id, requestDTO));
        
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
