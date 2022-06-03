package com.example.warehousespringlearn.controller;

import com.example.warehousespringlearn.entity.Measurement;
import com.example.warehousespringlearn.entity.Warehouse;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    public Page<Warehouse> getAllWarehouses(@RequestParam int page){
        return warehouseService.getAllWarehouse(page);
    }

    @GetMapping("/{id}")
    public Warehouse getWarehouseById(@PathVariable Integer id){
        return warehouseService.getWarehouseById(id);
    }

    @PostMapping
    public Result addWarehouse(@RequestBody Warehouse warehouse){
        return warehouseService.addWarehouse(warehouse);
    }

    @PutMapping("/{id}")
    public Result editWarehouse(@PathVariable Integer id, @RequestBody Warehouse warehouse){
        return warehouseService.editWarehouse(id, warehouse);
    }

    @DeleteMapping("/{id}")
    public Result deleteWarehouse(@PathVariable Integer id){
        return warehouseService.deleteWarehouseById(id);
    }
}
