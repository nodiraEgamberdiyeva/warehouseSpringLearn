package com.example.warehousespringlearn.repository;

import com.example.warehousespringlearn.entity.InputProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {

//    @Query(value = "select amount from input_product join product p on p.id = input_product.product_id where p.code=:code", nativeQuery = true)
//    Long getAmountByProductCode(String code);

    InputProduct findAllByProduct_Code(String product_code);

    @Query(value = "select sum(price) from input_product;", nativeQuery = true)
    BigDecimal getSumOfPrice();


}
