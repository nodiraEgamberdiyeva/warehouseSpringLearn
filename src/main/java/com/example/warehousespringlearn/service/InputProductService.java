package com.example.warehousespringlearn.service;

import com.example.warehousespringlearn.entity.*;
import com.example.warehousespringlearn.payload.InputProductDto;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.payload.UserDto;
import com.example.warehousespringlearn.repository.InputProductRepository;
import com.example.warehousespringlearn.repository.InputRepository;
import com.example.warehousespringlearn.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class InputProductService {
    private InputProductRepository inputProductRepository;
    private ProductRepository productRepository;
    private InputRepository inputRepository;

    @Autowired
    public InputProductService(InputProductRepository inputProductRepository, ProductRepository productRepository, InputRepository inputRepository) {
        this.inputProductRepository = inputProductRepository;
        this.productRepository = productRepository;
        this.inputRepository = inputRepository;
    }

    public Page<InputProduct> getAllInputProducts(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return inputProductRepository.findAll(pageable);
    }

    public InputProduct getInputProductById(Integer id){
        Optional<InputProduct> byId = inputProductRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }
        else return null;

    }

    public BigDecimal getSumOfPrice(){
        return inputProductRepository.getSumOfPrice();
    }

    public Result addInputProduct(InputProductDto inputProductDto){
        InputProduct inputProduct = new InputProduct();
        Optional<Product> productById = productRepository.findById(inputProductDto.getProductId());
        if (productById.isPresent()){
            inputProduct.setProduct(productById.get());
        }
        Optional<Input> inputById = inputRepository.findById(inputProductDto.getInputId());
        if (inputById.isPresent()){
            inputProduct.setInput(inputById.get());
        }
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProductRepository.save(inputProduct);
        return new Result("Input product is  added", false);
    }

    public Result editInputProduct(Integer id, InputProductDto inputProductDto){
        Optional<InputProduct> byId = inputProductRepository.findById(id);
        if (!byId.isPresent()){
            return new Result("inputProduct is not exist", false);
        }
        InputProduct inputProduct = byId.get();
        if (inputProductDto.getInputId()!=null){
            Optional<Input> inputById = inputRepository.findById(inputProductDto.getInputId());
            if (inputById.isPresent()){
                inputProduct.setInput(inputById.get());
            }
            else return new Result("input id is not exist", false);
        }
        if (inputProductDto.getProductId()!=null){
            Optional<Product> productById = productRepository.findById(inputProductDto.getProductId());
            if (productById.isPresent()){
                inputProduct.setProduct(productById.get());
            }
            else return new Result("product id is not exist", false);
        }
        if (inputProductDto.getAmount()!=null){
            inputProduct.setAmount(inputProductDto.getAmount());
        }
        if (inputProductDto.getPrice()!=null){
            inputProduct.setPrice(inputProductDto.getPrice());
        }
        if (inputProductDto.getExpireDate()!=null){
            inputProduct.setExpireDate(inputProductDto.getExpireDate());
        }

        inputProductRepository.save(inputProduct);
        return new Result("inputProduct is edited", true);

    }

    public Result deleteInputProductById(Integer id){
        Optional<InputProduct> byId = inputProductRepository.findById(id);
        if (byId.isPresent()){
            inputProductRepository.deleteById(id);
            return new Result("deleted", true);
        }
        else return new Result("id is not exist", false);

    }
}
