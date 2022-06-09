package com.example.warehousespringlearn.service;

import com.example.warehousespringlearn.entity.*;
import com.example.warehousespringlearn.payload.OutputDto;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OutputService {
    private OutputRepository outputRepository;
    private WarehouseRepository warehouseRepository;
    private ClientRepository clientRepository;
    private CurrencyRepository currencyRepository;

    @Autowired
    public OutputService(OutputRepository outputRepository,
                         WarehouseRepository warehouseRepository,
                         ClientRepository clientRepository,
                         CurrencyRepository currencyRepository) {
        this.outputRepository = outputRepository;
        this.warehouseRepository = warehouseRepository;
        this.clientRepository = clientRepository;
        this.currencyRepository = currencyRepository;
    }

    public Page<Output> getAllOutputs(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return outputRepository.findAll(pageable);
    }

    public Output getOutputById(Integer id){
        Optional<Output> byId = outputRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }
        else return null;

    }

    public Result addOutput(OutputDto outputDto){
        if (outputRepository.existsByCode(outputDto.getCode())){
            return new Result("output is already exist", false);
        }
        Optional<Warehouse> warehouseById = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!warehouseById.isPresent()){
            return new Result("warehouse id is not exist", false);
        }
        Optional<Client> clientById = clientRepository.findById(outputDto.getClientId());
        if (!clientById.isPresent()){
            return new Result("client id is not exist", false);
        }
        Optional<Currency> currencyById = currencyRepository.findById(outputDto.getCurrencyId());
        if (!currencyById.isPresent()){
            return new Result("currency id is not exist", false);
        }

        Output output = new Output();
        output.setCode(outputDto.getCode());
        output.setCurrency(currencyById.get());
        output.setClient(clientById.get());
        output.setWarehouse(warehouseById.get());
        output.setFactureNumber(outputDto.getFactureNumber());
        outputDto.setDate(outputDto.getDate());
        outputRepository.save(output);
        return new Result("output is added", true);


    }

    public Result editOutput(Integer id, OutputDto outputDto){
        Optional<Output> outputById = outputRepository.findById(id);
        if (!outputById.isPresent()){
            return new Result("output id is not found", false);
        }

        Output output = outputById.get();

        if (outputDto.getCode()!=null){
            if ((!output.getCode().equals(outputDto.getCode()))&&
                    outputRepository.existsByCode(outputDto.getCode())){
                return new Result("output is already exist", false);
            }
            output.setCode(outputDto.getCode());
        }

        if (outputDto.getWarehouseId()!=null){
            Optional<Warehouse> warehouseById = warehouseRepository.findById(outputDto.getWarehouseId());
            if (!warehouseById.isPresent()){
                return new Result("warehouse id is not exist", false);
            }
            output.setWarehouse(warehouseById.get());
        }

        if (outputDto.getClientId()!=null){
            Optional<Client> clientById = clientRepository.findById(outputDto.getClientId());
            if (!clientById.isPresent()){
                return new Result("client id is not exist", false);
            }
            output.setClient(clientById.get());
        }

        if (outputDto.getCurrencyId()!=null){
            Optional<Currency> currencyById = currencyRepository.findById(outputDto.getCurrencyId());
            if (!currencyById.isPresent()){
                return new Result("currency id is not exist", false);
            }
            output.setCurrency(currencyById.get());
        }

        if (outputDto.getDate()!=null){
            output.setDate(outputDto.getDate());
        }

        if (outputDto.getFactureNumber()!=null){
            output.setFactureNumber(outputDto.getFactureNumber());
        }
        outputRepository.save(output);
        return new Result("output is edited", true);

    }

    public Result deleteOutputById(Integer id){
        Optional<Output> byId = outputRepository.findById(id);
        if (byId.isPresent()){
            outputRepository.deleteById(id);
            return new Result("input is deleted", true);
        }
        else return new Result("id is not exist", false);
    }
}
