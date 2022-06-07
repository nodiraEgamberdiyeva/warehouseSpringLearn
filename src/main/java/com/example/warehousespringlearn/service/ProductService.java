package com.example.warehousespringlearn.service;

import com.example.warehousespringlearn.entity.*;
import com.example.warehousespringlearn.payload.ProductDto;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.repository.AttachmentRepository;
import com.example.warehousespringlearn.repository.CategoryRepository;
import com.example.warehousespringlearn.repository.MeasurementRepository;
import com.example.warehousespringlearn.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private AttachmentRepository attachmentRepository;
    private MeasurementRepository measurementRepository;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository,
                          AttachmentRepository attachmentRepository,
                          MeasurementRepository measurementRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.attachmentRepository = attachmentRepository;
        this.measurementRepository = measurementRepository;
    }




    public Page<Product> getAllProducts(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return productRepository.findAll(pageable);
    }

    public Product getProductById(Integer id){
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }
        else return null;

    }

    public Result addProduct(ProductDto productDto){
       if (productRepository.existsByCode(productDto.getCode())){
           return new Result("product is exist", false);
       }
        Optional<Category> categoryById = categoryRepository.findById(productDto.getCategoryId());
       if (!categoryById.isPresent()){
           return new Result("category is not exist", false);
       }
        Optional<Measurement> measurementById = measurementRepository.findById(productDto.getMeasurementId());
       if (!measurementById.isPresent()){
           return new Result("measurement is not exist", false);
       }
       else{
           Set<Attachment> attachments = new HashSet<>();
           Set<Integer> attachmentIds = productDto.getAttachmentIds();
           for (Integer attachmentId : attachmentIds) {
               Optional<Attachment> byId1 = attachmentRepository.findById(attachmentId);
               if (byId1.isPresent()){
                   attachments.add(byId1.get());
               }
               else return new Result("attachment with"+attachmentId+" id is not exist", false);
           }
            Product product = new Product();
            product.setCategory(categoryById.get());
            product.setMeasurement(measurementById.get());
           if (attachments.size()!=0){
               product.setAttachment(attachments);
           }
            product.setCode(productDto.getCode());
            product.setName(productDto.getName());
            product.setStatus(productDto.isStatus());
            productRepository.save(product);
            return new Result("product is added", true);
        }

    }

    public Result editProduct(Integer id, ProductDto productDto){
        Optional<Product> byId = productRepository.findById(id);
        if (!byId.isPresent()){
            return new Result("id is not exist", false);
        }
        else {
            Product product = byId.get();
            if (productDto.getCategoryId()!=null){
                Optional<Category> categoryById = categoryRepository.findById(productDto.getCategoryId());
                if (categoryById.isPresent()){
                    product.setCategory(categoryById.get());
                }
                else return new Result("category is not exist", false);
            }
            if (productDto.getMeasurementId()!=null){
                Optional<Measurement> measurementById = measurementRepository.findById(productDto.getMeasurementId());
                if (measurementById.isPresent()){
                    product.setMeasurement(measurementById.get());
                }
                else return new Result("Measurement is not exist", false);
            }
            if (productDto.getAttachmentIds()!=null){
                Set<Attachment> attachments = new HashSet<>();
                Set<Integer> attachmentIds = productDto.getAttachmentIds();
                for (Integer attachmentId : attachmentIds) {
                    Optional<Attachment> byId1 = attachmentRepository.findById(attachmentId);
                    if (byId1.isPresent()){
                        attachments.add(byId1.get());
                    }
                    else return new Result("attachment with"+attachmentId+" id is not exist", false);
                }
                if (attachments.size()!=0){
                    product.setAttachment(attachments);
                }
            }
            if (productDto.getCode()!=null){
                product.setCode(productDto.getCode());
            }
            if (productDto.getName()!=null){
                product.setName(productDto.getName());
            }
            productRepository.save(product);
            return new Result("product is edited", true);


        }

    }

    public Result deleteProductById(Integer id){
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()){
            productRepository.deleteById(id);
            return new Result("deleted", true);
        }
        else return new Result("id is not exist", false);

    }
}
