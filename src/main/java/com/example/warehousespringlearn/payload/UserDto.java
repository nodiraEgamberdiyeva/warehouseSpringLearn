package com.example.warehousespringlearn.payload;

import com.example.warehousespringlearn.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String firstName;
    private String lastName;

    private String phoneNumber;
    private String code;

    private String password;
    private boolean active=true;

    private Set<Integer> warehousesIds;
}
