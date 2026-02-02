package com.devsuperior.gscommerce.service;


import com.devsuperior.gscommerce.entities.dto.ProductRequestDTO;
import com.devsuperior.gscommerce.entities.Product;
import com.devsuperior.gscommerce.entities.dto.ProductResponseDTO;
import com.devsuperior.gscommerce.exceptions.ResourceNotFoundException;
import com.devsuperior.gscommerce.mapper.ProductMapper;
import com.devsuperior.gscommerce.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponseDTO insert(ProductRequestDTO requestDTO){
        Product entity = new Product();
        entity.setName(requestDTO.name());
        entity.setDescription(requestDTO.description());
        entity.setPrice(requestDTO.price());
        entity.setImgUrl(requestDTO.imgUrl());
        entity = productRepository.save(entity);
        return ProductMapper.toDTO(entity);
    }

    @Transactional
    public ProductResponseDTO update (Long id, ProductRequestDTO productRequestDTO){
        Product entity = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Id nao encontrado " + id));
            entity.setName(productRequestDTO.name());
            entity.setDescription(productRequestDTO.description());
            entity.setPrice(productRequestDTO.price());
            entity.setImgUrl(productRequestDTO.imgUrl());
        return ProductMapper.toDTO(entity);

    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> findAll(Pageable pageable){
        Page<Product> entity = productRepository.findAll(pageable);
        return entity.map(ProductMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO findById(Long id){
        Product entity = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Id nao encontrado "+ id));

        return ProductMapper.toDTO(entity);
    }

    @Transactional
    public void delete(Long id){
        productRepository.deleteById(id);
    }


}
