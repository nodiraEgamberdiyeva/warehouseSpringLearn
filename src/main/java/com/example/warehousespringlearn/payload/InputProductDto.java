package com.example.warehousespringlearn.payload;

import com.example.warehousespringlearn.entity.Input;
import com.example.warehousespringlearn.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InputProductDto {
    private Integer productId;
    private Long amount;
    private BigDecimal price;
    private Date expireDate;

    private Integer inputId;
}
