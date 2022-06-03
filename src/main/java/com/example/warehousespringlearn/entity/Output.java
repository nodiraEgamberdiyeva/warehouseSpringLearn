package com.example.warehousespringlearn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Timestamp date;

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Currency currency;

    @ManyToOne
    private Client client;
    @Column(nullable = false)
    private String factureNumber;
    @Column(unique = true, nullable = false)
    private String code;





}
