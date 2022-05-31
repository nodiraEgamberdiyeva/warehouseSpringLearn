package com.example.warehousespringlearn.entity;

import com.example.warehousespringlearn.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Product extends AbstractEntity {
    @ManyToOne
    private Category category;
    @OneToMany
    private Set<Attachment> attachment;
    @ManyToOne
    private Measurement measurement;
    private String code;

}
