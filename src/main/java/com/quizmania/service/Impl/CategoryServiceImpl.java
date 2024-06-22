package com.quizmania.service.Impl;

import com.quizmania.Repositories.CategoryRepository;
import com.quizmania.dto.ApiResponse;
import com.quizmania.entity.Category;
import com.quizmania.error.ResourceNotFoundException;
import com.quizmania.service.CatogoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CatogoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category addCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {

        Optional<Category> retrivedCategory = this.categoryRepository.findById(category.getCid());
        if(retrivedCategory.isEmpty()) throw new ResourceNotFoundException("Category","with Cid",""+category.getCid());
        return this.categoryRepository.save(category);
    }

    @Override
    public Category getCategory(Long cid) {

        Optional<Category> retrivedCategory = this.categoryRepository.findById(cid);
        if(retrivedCategory.isEmpty()) throw new ResourceNotFoundException("Category","with Cid",""+cid);
        return retrivedCategory.get();
    }

    @Override
    public Set<Category> getCategories() {
        return new LinkedHashSet<>(this.categoryRepository.findAll());
    }

    @Override
    public ApiResponse deleteCategory(Long cid) {
        Optional<Category> retrivedCategory = this.categoryRepository.findById(cid);
        if(retrivedCategory.isEmpty()) throw new ResourceNotFoundException("Category","with Cid",""+cid);
        Category category= new Category();
        category.setCid(cid);
        this.categoryRepository.delete(category);
        return new ApiResponse("Category Deleted Successfully ");

    }
}
