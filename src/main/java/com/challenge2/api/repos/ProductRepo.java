package com.challenge2.api.repos;

import com.challenge2.api.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
