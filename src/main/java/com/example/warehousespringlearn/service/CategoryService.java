package com.example.warehousespringlearn.service;

import com.example.warehousespringlearn.entity.Category;
import com.example.warehousespringlearn.entity.Measurement;
import com.example.warehousespringlearn.payload.CategoryDto;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> getAllCategory(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return categoryRepository.findAll(pageable);
    }

    public Category getCategoryById(Integer id){
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }
        else return null;

    }

    public Result addCategory(CategoryDto categoryDto){
        if (!categoryRepository.existsByName(categoryDto.getName())) {
            if (categoryDto.getParentCategoryId()!=null) {
                Optional<Category> byId = categoryRepository.findById(categoryDto.getParentCategoryId());
                if (byId.isPresent()){
                    Category categoryParent = byId.get();
                    categoryRepository.save(categorySet(new Category(), categoryDto, categoryParent));
                    return new Result("category is added", true);
                }
                else return new Result("category parent is not exist", false);
            }
            else {
                categoryRepository.save(categorySet(new Category(), categoryDto, null));
                return new Result("category is added", true);
            }
        }
        else {
            return new Result("category is already exist", false);
        }
    }

    public Result editCategory(Integer id, CategoryDto categoryDto){
        if (id== categoryDto.getParentCategoryId()){
            return new Result("parent id can not be same with category id", false);
        }
        Optional<Category> categoryById = categoryRepository.findById(id);
        if (categoryById.isPresent()){
            Category category = categoryById.get();
            if (categoryDto.getParentCategoryId()==null){
                categoryRepository.save(categorySet(category, categoryDto, null));
                return new Result("edited", true);
            }
            else {
                Optional<Category> parenCatById = categoryRepository.findById(categoryDto.getParentCategoryId());
                if (parenCatById.isPresent()) {
                    Category parentCategory = parenCatById.get();
                    categoryRepository.save(categorySet(category, categoryDto, parentCategory));
                    return new Result("edited", true);
                } else {
                    return new Result("parent category is not exist", false);
                }
            }
        }
        else return new Result("id is not found", false);
    }

    public Result deleteCategoryById(Integer id){
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()){
            List<Category> categories = categoryRepository.findAll();
            for (int i = 0; i < categories.size(); i++) {
               if (categories.get(i).getParentCategory()!=null
                       &&categories.get(i).getParentCategory().getId()==id){
                   categoryRepository.deleteById(categories.get(i).getId());
               }
            }
            categoryRepository.deleteById(id);
            return new Result("deleted", true);
        }
        else return new Result("id is not exist", false);
    }

    private Category categorySet(Category categoryToSave, CategoryDto categoryDto, Category parentCategory){

        categoryToSave.setParentCategory(parentCategory);
        categoryToSave.setName(categoryDto.getName());
        categoryToSave.setStatus(categoryDto.isStatus());
        return categoryToSave;
    }


}
