package com.example.warehousespringlearn.repository;

import com.example.warehousespringlearn.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByCode(String code);

    Product findAllByCode(String code);
}
