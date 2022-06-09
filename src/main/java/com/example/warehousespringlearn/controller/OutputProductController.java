package com.example.warehousespringlearn.controller;

import com.example.warehousespringlearn.entity.OutputProduct;
import com.example.warehousespringlearn.payload.OutputProductDto;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.service.OutputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/output-product")
public class OutputProductController {
    private final OutputProductService outputProductService;

    @Autowired
    public OutputProductController(OutputProductService outputProductService) {
        this.outputProductService = outputProductService;
    }

    @GetMapping
    public Page<OutputProduct> getAllOutputProducts(@RequestParam int page){
        return outputProductService.getAllOutputProducts(page);
    }

    @GetMapping("/{id}")
    public OutputProduct getOutputProductById(@PathVariable Integer id){
        return outputProductService.getOutputProductById(id);
    }

    @PostMapping
    public Result addOutputProduct(@RequestBody OutputProductDto outputProductDto){
        return outputProductService.addOutputProduct(outputProductDto);
    }

    @PutMapping("/{id}")
    public Result editOutputProduct(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto){
        return outputProductService.editOutputProduct(id, outputProductDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteOutputProduct(@PathVariable Integer id){
        return outputProductService.deleteOutputProductById(id);
    }


}
