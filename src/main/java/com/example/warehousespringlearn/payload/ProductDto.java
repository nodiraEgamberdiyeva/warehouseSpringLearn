package com.example.warehousespringlearn.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private String name;
    private boolean status;
    private Integer categoryId;

    private Set<Integer> attachmentIds;

    private Integer measurementId;
    private String code;
}
