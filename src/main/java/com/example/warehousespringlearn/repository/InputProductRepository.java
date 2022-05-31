package com.example.warehousespringlearn.repository;

import com.example.warehousespringlearn.entity.InputProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {
}
