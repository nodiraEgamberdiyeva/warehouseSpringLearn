package com.example.warehousespringlearn.service;

import com.example.warehousespringlearn.entity.*;
import com.example.warehousespringlearn.payload.OutputProductDto;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.repository.InputProductRepository;
import com.example.warehousespringlearn.repository.OutputProductRepository;
import com.example.warehousespringlearn.repository.OutputRepository;
import com.example.warehousespringlearn.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OutputProductService {
    private OutputProductRepository outputProductRepository;
    private ProductRepository productRepository;
    private OutputRepository outputRepository;
    private InputProductRepository inputProductRepository;

    @Autowired
    public OutputProductService(OutputProductRepository outputProductRepository,
                                ProductRepository productRepository,
                                OutputRepository outputRepository,
                                InputProductRepository inputProductRepository) {
        this.outputProductRepository = outputProductRepository;
        this.productRepository = productRepository;
        this.outputRepository = outputRepository;
        this.inputProductRepository = inputProductRepository;
    }

    public Page<OutputProduct> getAllOutputProducts(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return outputProductRepository.findAll(pageable);
    }

    public OutputProduct getOutputProductById(Integer id){
        Optional<OutputProduct> byId = outputProductRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }
        else return null;

    }

    public Result addOutputProduct(OutputProductDto outputProductDto){
        Long inputProductAmount = getInputProductAmount(outputProductDto);

        if (outputProductDto.getAmount()>inputProductAmount){
            return new Result("there are "+
                    inputProductAmount+" "+
                    productRepository.findById(outputProductDto.getProductId()).get().getName()+
                    "s left", false);
        }
        OutputProduct outputProduct = new OutputProduct();
        Optional<Product> productById = productRepository.findById(outputProductDto.getProductId());
        if (productById.isPresent()){
            outputProduct.setProduct(productById.get());
        }
        Optional<Output> outputById = outputRepository.findById(outputProductDto.getOutputId());
        if (outputById.isPresent()){
            outputProduct.setOutput(outputById.get());
        }
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProductRepository.save(outputProduct);
        return new Result("Output product is  added", false);
    }

    public Result editOutputProduct(Integer id, OutputProductDto outputProductDto){
        Optional<OutputProduct> byId = outputProductRepository.findById(id);
        if (!byId.isPresent()){
            return new Result("outputProduct is not exist", false);
        }
        OutputProduct outputProduct = byId.get();
        if (outputProductDto.getOutputId()!=null){
            Optional<Output> outputById = outputRepository.findById(outputProductDto.getOutputId());
            if (outputById.isPresent()){
                outputProduct.setOutput(outputById.get());
            }
            else return new Result("output id is not exist", false);
        }
        if (outputProductDto.getProductId()!=null){
            Optional<Product> productById = productRepository.findById(outputProductDto.getProductId());
            if (productById.isPresent()){
                outputProduct.setProduct(productById.get());
            }
            else return new Result("product id is not exist", false);
        }
        if (outputProductDto.getAmount()!=null){
            outputProduct.setAmount(outputProductDto.getAmount());
        }
        if (outputProductDto.getPrice()!=null){
            outputProduct.setPrice(outputProductDto.getPrice());
        }

        outputProductRepository.save(outputProduct);
        return new Result("outputProduct is edited", true);

    }

    public Result deleteOutputProductById(Integer id){
        Optional<OutputProduct> byId = outputProductRepository.findById(id);
        if (byId.isPresent()){
            outputProductRepository.deleteById(id);
            return new Result("deleted", true);
        }
        else return new Result("id is not exist", false);

    }

    Long getInputProductAmount(OutputProductDto outputProductDto){
        Optional<Product> productById = productRepository.findById(outputProductDto.getProductId());
        if (!productById.isPresent()){
            return null;
        }
        Product product = productById.get();
        InputProduct allByProduct_code = inputProductRepository.findAllByProduct_Code(product.getCode());
        return allByProduct_code.getAmount();
    }


}
