package com.example.warehousespringlearn.controller;

import com.example.warehousespringlearn.entity.InputProduct;
import com.example.warehousespringlearn.entity.User;
import com.example.warehousespringlearn.payload.InputProductDto;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.payload.UserDto;
import com.example.warehousespringlearn.service.InputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/input-product")
public class InputProductController {
    private InputProductService inputProductService;

    @Autowired
    public InputProductController(InputProductService inputProductService) {
        this.inputProductService = inputProductService;
    }

    @GetMapping
    public Page<InputProduct> getAllInputProducts(@RequestParam int page){
        return inputProductService.getAllInputProducts(page);
    }

    @GetMapping("/{id}")
    public InputProduct getInputProductById(@PathVariable Integer id){
        return inputProductService.getInputProductById(id);
    }

    @GetMapping("/expense")
    public BigDecimal getAmountOfExpense(){
        return inputProductService.getSumOfPrice();
    }

    @PostMapping
    public Result addInputProduct(@RequestBody InputProductDto inputProductDto){
        return inputProductService.addInputProduct(inputProductDto);
    }

    @PutMapping("/{id}")
    public Result editInputProduct(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto){
        return inputProductService.editInputProduct(id, inputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteInputProduct(@PathVariable Integer id){
        return inputProductService.deleteInputProductById(id);
    }
}
