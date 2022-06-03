package com.example.warehousespringlearn.repository;

import com.example.warehousespringlearn.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    boolean existsByPhoneNumber(String name);
}
