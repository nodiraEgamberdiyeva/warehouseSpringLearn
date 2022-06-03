package com.example.warehousespringlearn.service;

import com.example.warehousespringlearn.entity.Currency;
import com.example.warehousespringlearn.entity.Measurement;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;

    public Page<Currency> getAllCurrency(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return currencyRepository.findAll(pageable);
    }

    public Currency getCurrencyById(Integer id){
        Optional<Currency> byId = currencyRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }
        else return null;

    }

    public Result addCurrency(Currency currency){
        if (currencyRepository.existsByName(currency.getName())) {
            return new Result("currency is exist", false);
        }
        else {
            currencyRepository.save(currency);
            return new Result("currency is added", true);
        }
    }

    public Result editCurrency(Integer id, Currency currency){
        Optional<Currency> byId = currencyRepository.findById(id);
        if (byId.isPresent()){
            Currency currency1 = byId.get();
            currency1.setName(currency.getName());
            currency1.setStatus(currency.isStatus());
            currencyRepository.save(currency1);
            return new Result("edited", true);
        }
        else return new Result("id is not found", false);
    }

    public Result deleteMCurrencyById(Integer id){
        Optional<Currency> byId = currencyRepository.findById(id);
        if (byId.isPresent()){
            currencyRepository.deleteById(id);
            return new Result("deleted", true);
        }
        else return new Result("id is not exist", false);

    }
}
