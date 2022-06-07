package com.example.warehousespringlearn.service;

import com.example.warehousespringlearn.entity.*;
import com.example.warehousespringlearn.payload.InputDto;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.repository.CurrencyRepository;
import com.example.warehousespringlearn.repository.InputRepository;
import com.example.warehousespringlearn.repository.SupplierRepository;
import com.example.warehousespringlearn.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InputService {
    private InputRepository inputRepository;
    private WarehouseRepository warehouseRepository;
    private SupplierRepository supplierRepository;
    private CurrencyRepository currencyRepository;

    @Autowired
    public InputService(InputRepository inputRepository, WarehouseRepository warehouseRepository, SupplierRepository supplierRepository, CurrencyRepository currencyRepository) {
        this.inputRepository = inputRepository;
        this.warehouseRepository = warehouseRepository;
        this.supplierRepository = supplierRepository;
        this.currencyRepository = currencyRepository;
    }

    public Page<Input> getAllInput(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return inputRepository.findAll(pageable);
    }

    public Input getInputById(Integer id){
        Optional<Input> byId = inputRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }
        else return null;

    }

    public Result addInput(InputDto inputDto){
        if (inputRepository.existsByCode(inputDto.getCode())){
            return new Result("input is already exist", false);
        }
        Optional<Warehouse> warehouseById = warehouseRepository.findById(inputDto.getWarehouseId());
        Optional<Supplier> supplierById = supplierRepository.findById(inputDto.getSupplierId());
        Optional<Currency> currencyById = currencyRepository.findById(inputDto.getCurrencyId());
        if (warehouseById.isPresent()&&
                supplierById.isPresent()&&
                currencyById.isPresent()){
            Input input = new Input();
            input.setDate(inputDto.getDate());
            input.setWarehouse(warehouseById.get());
            input.setCurrency(currencyById.get());
            input.setSupplier(supplierById.get());
            input.setFactureNumber(inputDto.getFactureNumber());
            input.setCode(inputDto.getCode());
            inputRepository.save(input);
            return new Result("input is added", true);
        }
        else return new Result("please check ids", false);


    }

    public Result editInput(Integer id, InputDto inputDto){
        Optional<Input> inputById = inputRepository.findById(id);
        if (inputById.isPresent()){
            Input input = inputById.get();
            if (inputDto.getDate()!=null){
                input.setDate(inputDto.getDate());
            }
            if (inputDto.getWarehouseId()!=null){
                Optional<Warehouse> warehouseById = warehouseRepository.findById(inputDto.getWarehouseId());
                if (warehouseById.isPresent()){
                    input.setWarehouse(warehouseById.get());
                }else return new Result("warehouse is not exist", false);
            }
            if (inputDto.getCurrencyId()!=null){
                Optional<Currency> currencyById = currencyRepository.findById(inputDto.getCurrencyId());
                if (currencyById.isPresent()){
                    input.setCurrency(currencyById.get());
                }else return new Result("currency is not exist", false);
            }
            if (inputDto.getSupplierId()!=null){
                Optional<Supplier> supplierById = supplierRepository.findById(inputDto.getSupplierId());
                if (supplierById.isPresent()){
                    input.setSupplier(supplierById.get());
                }else return new Result("supplier is not exist", false);
            }
            if (inputDto.getFactureNumber()!=null){
                input.setFactureNumber(inputDto.getFactureNumber());
            }
            if (inputDto.getCode()!=null){
                if (!inputRepository.existsByCode(inputDto.getCode())) {
                    input.setCode(inputDto.getCode());
                }
                else return new Result("input with this code is already exist", false);
            }
            inputRepository.save(input);
            return new Result("input is edited", true);

        }
        else return new Result("id is not exist", false);

    }

    public Result deleteInputById(Integer id){
        Optional<Input> byId = inputRepository.findById(id);
        if (byId.isPresent()){
            inputRepository.deleteById(id);
            return new Result("input is deleted", true);
        }
        else return new Result("id is not exist", false);
    }


}
