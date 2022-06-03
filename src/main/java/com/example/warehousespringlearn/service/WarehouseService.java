package com.example.warehousespringlearn.service;

import com.example.warehousespringlearn.entity.Measurement;
import com.example.warehousespringlearn.entity.Warehouse;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    public Page<Warehouse> getAllWarehouse(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return warehouseRepository.findAll(pageable);
    }

    public Warehouse getWarehouseById(Integer id){
        Optional<Warehouse> byId = warehouseRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }
        else return null;

    }

    public Result addWarehouse(Warehouse warehouse){
        if (warehouseRepository.existsByName(warehouse.getName())) {
            return new Result("warehouse is exist", false);
        }
        else {
            warehouseRepository.save(warehouse);
            return new Result("warehouse is added", true);
        }
    }

    public Result editWarehouse(Integer id, Warehouse warehouse){
        Optional<Warehouse> byId = warehouseRepository.findById(id);
        if (byId.isPresent()){
            Warehouse warehouse1 = byId.get();
            warehouse1.setName(warehouse.getName());
            warehouse1.setStatus(warehouse.isStatus());
            warehouseRepository.save(warehouse1);
            return new Result("edited", true);
        }
        else return new Result("id is not found", false);
    }

    public Result deleteWarehouseById(Integer id){
        Optional<Warehouse> byId = warehouseRepository.findById(id);
        if (byId.isPresent()){
            warehouseRepository.deleteById(id);
            return new Result("deleted", true);
        }
        else return new Result("id is not exist", false);

    }
}
