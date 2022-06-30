package com.example.warehousespringlearn.controller;

import com.example.warehousespringlearn.entity.Category;
import com.example.warehousespringlearn.payload.CategoryDto;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasAnyAuthority('READ_ALL_CATEGORY', 'ALL')")
    @GetMapping
    public Page<Category> getAllCategories(@RequestParam int page){
        return categoryService.getAllCategory(page);
    }

    @PreAuthorize("hasAnyAuthority('READ_CATEGORY', 'ALL')")
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id){
        return categoryService.getCategoryById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADD_CATEGORY', 'ALL')")
    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.addCategory(categoryDto);
    }

    @PreAuthorize("hasAnyAuthority('EDIT_CATEGORY', 'ALL')")
    @PutMapping("/{id}")
    public Result editCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        return categoryService.editCategory(id, categoryDto);
    }

    @PreAuthorize("hasAnyAuthority('DELETE_CATEGORY', 'ALL')")
    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id){
        return categoryService.deleteCategoryById(id);
    }
}
