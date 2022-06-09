package com.example.warehousespringlearn.payload;

import com.example.warehousespringlearn.entity.Output;
import com.example.warehousespringlearn.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutputProductDto {

    private Integer productId;
    private Long amount;
    private BigDecimal price;
    private Integer outputId;
}
