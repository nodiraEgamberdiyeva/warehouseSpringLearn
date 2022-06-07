package com.example.warehousespringlearn.controller;

import com.example.warehousespringlearn.entity.Supplier;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public Page<Supplier> getAllSuppliers(@RequestParam int page){
        return supplierService.getAllSuppliers(page);
    }

    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable Integer id){
        return supplierService.getSupplierById(id);
    }

    @PostMapping
    public Result addSupplier(@RequestBody Supplier supplier){
        return supplierService.addSupplier(supplier);
    }

    @PutMapping("/{id}")
    public Result editSupplier(@PathVariable Integer id, @RequestBody Supplier supplier){
        return supplierService.editSupplier(id, supplier);
    }

    @DeleteMapping("/{id}")
    public Result deleteSupplier(@PathVariable Integer id){
        return supplierService.deleteSupplierById(id);
    }
}
