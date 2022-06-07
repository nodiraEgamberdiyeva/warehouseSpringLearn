package com.example.warehousespringlearn.service;

import com.example.warehousespringlearn.entity.Supplier;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplierService {

    private SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository, OtherService otherService) {
        this.supplierRepository = supplierRepository;
        this.otherService = otherService;
    }

    private OtherService otherService;

    public Page<Supplier> getAllSuppliers(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return supplierRepository.findAll(pageable);
    }

    public Supplier getSupplierById(Integer id){
        Optional<Supplier> byId = supplierRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }
        else return null;

    }

    public Result addSupplier(Supplier supplier){
        String phoneNumber = otherService.phoneNumberToSave(supplier.getPhoneNumber());
        if (phoneNumber!=null) {
            supplier.setPhoneNumber(phoneNumber);
            if (supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber())) {
                return new Result("Supplier is exist", false);
            }
            else {
                supplierRepository.save(supplier);
                return new Result("Supplier is added", true);
            }

        }
        else return new Result("phone number is incorrect", false);
    }

    public Result editSupplier(Integer id, Supplier supplier){
        Optional<Supplier> byId = supplierRepository.findById(id);
        if (byId.isPresent()){
            Supplier supplier1 = byId.get();
            supplier1.setPhoneNumber(supplier.getPhoneNumber());
            supplier1.setName(supplier.getName());
            supplier1.setStatus(supplier.isStatus());
            supplierRepository.save(supplier1);
            return new Result("edited", true);
        }
        else return new Result("id is not found", false);
    }

    public Result deleteSupplierById(Integer id){
        Optional<Supplier> byId = supplierRepository.findById(id);
        if (byId.isPresent()){
            supplierRepository.deleteById(id);
            return new Result("deleted", true);
        }
        else return new Result("id is not exist", false);

    }


}
