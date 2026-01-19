package com.devsuperior.gscommerce.repositories;

import com.devsuperior.gscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
