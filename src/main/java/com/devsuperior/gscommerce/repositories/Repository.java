package com.devsuperior.gscommerce.repositories;

import com.devsuperior.gscommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<User, Long> {
}
