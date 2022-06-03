package com.example.warehousespringlearn.controller;

import com.example.warehousespringlearn.entity.Currency;
import com.example.warehousespringlearn.entity.Measurement;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public Page<Currency> getAllCurrencys(@RequestParam int page){
        return currencyService.getAllCurrency(page);
    }

    @GetMapping("/{id}")
    public Currency getCurrencyById(@PathVariable Integer id){
        return currencyService.getCurrencyById(id);
    }

    @PostMapping
    public Result addCurrency(@RequestBody Currency currency){
        return currencyService.addCurrency(currency);
    }

    @PutMapping("/{id}")
    public Result editCurrency(@PathVariable Integer id, @RequestBody Currency currency){
        return currencyService.editCurrency(id, currency);
    }

    @DeleteMapping("/{id}")
    public Result deleteCurrency(@PathVariable Integer id){
        return currencyService.deleteMCurrencyById(id);
    }
}
