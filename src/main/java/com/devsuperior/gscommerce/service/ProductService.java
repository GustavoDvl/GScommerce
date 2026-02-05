package com.devsuperior.gscommerce.service;


import com.devsuperior.gscommerce.dto.ProductRequestDTO;
import com.devsuperior.gscommerce.entities.Product;
import com.devsuperior.gscommerce.dto.ProductResponseDTO;
import com.devsuperior.gscommerce.mapper.ProductMapper;
import com.devsuperior.gscommerce.repositories.ProductRepository;
import com.devsuperior.gscommerce.service.exceptions.CustomError;
import com.devsuperior.gscommerce.service.exceptions.DataBaseException;
import com.devsuperior.gscommerce.service.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
                .orElseThrow(()-> new ResourceNotFoundException("Recurso nao encontrado "+ id));

        return ProductMapper.toDTO(entity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade referencial");
        }
    }


}
