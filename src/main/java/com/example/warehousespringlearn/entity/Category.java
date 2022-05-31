package com.example.warehousespringlearn.entity;

import com.example.warehousespringlearn.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Category extends AbstractEntity {

    @ManyToOne
    private Category parentCategory;
}
