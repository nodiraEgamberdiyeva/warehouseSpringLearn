package com.example.warehousespringlearn.entity;

import com.example.warehousespringlearn.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Client extends AbstractEntity {
    @Column(unique = true, nullable = false)
    private String phoneNumber;
}
