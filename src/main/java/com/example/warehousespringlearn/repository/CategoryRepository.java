package com.example.warehousespringlearn.repository;

import com.example.warehousespringlearn.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);


}
