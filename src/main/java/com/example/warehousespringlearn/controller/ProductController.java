package com.example.warehousespringlearn.controller;

import com.example.warehousespringlearn.entity.Input;
import com.example.warehousespringlearn.entity.Product;
import com.example.warehousespringlearn.payload.InputDto;
import com.example.warehousespringlearn.payload.ProductDto;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<Product> getAllProducts(@RequestParam int page){
        return productService.getAllProducts(page);
    }

    @GetMapping("/{id}")
    public Product getIProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    @PostMapping
    public Result addIProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }

    @PutMapping("/{id}")
    public Result editProduct(@PathVariable Integer id, @RequestBody ProductDto productDto){
        return productService.editProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteProduct(@PathVariable Integer id){
        return productService.deleteProductById(id);
    }
}
