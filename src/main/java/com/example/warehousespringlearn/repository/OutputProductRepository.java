package com.example.warehousespringlearn.repository;

import com.example.warehousespringlearn.entity.OutputProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutputProductRepository extends JpaRepository<OutputProduct, Integer> {
}
