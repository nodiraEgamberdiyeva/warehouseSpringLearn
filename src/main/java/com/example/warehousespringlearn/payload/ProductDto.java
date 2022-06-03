package com.example.warehousespringlearn.payload;

import com.example.warehousespringlearn.entity.Attachment;
import com.example.warehousespringlearn.entity.Category;
import com.example.warehousespringlearn.entity.Measurement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
