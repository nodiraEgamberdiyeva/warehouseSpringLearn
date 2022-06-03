package com.example.warehousespringlearn.payload;

import com.example.warehousespringlearn.entity.Client;
import com.example.warehousespringlearn.entity.Currency;
import com.example.warehousespringlearn.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutputDto {

    private Timestamp date;

    private Integer warehouseId;

    private Integer currencyId;

    private Integer clientId;

    private String factureNumber;

    private String code;
}
