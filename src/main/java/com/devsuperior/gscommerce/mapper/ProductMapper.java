package com.devsuperior.gscommerce.mapper;

import com.devsuperior.gscommerce.entities.Product;
import com.devsuperior.gscommerce.dto.ProductResponseDTO;

public class ProductMapper {

    public ProductMapper() {
    }

    public static ProductResponseDTO toDTO(Product product){
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImgUrl()
        );
    }
}
