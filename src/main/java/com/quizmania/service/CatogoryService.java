package com.quizmania.service;

import com.quizmania.dto.ApiResponse;
import com.quizmania.entity.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface CatogoryService {
    public Category addCategory(Category category);

    public Category  updateCategory(Category category);
    public Category  getCategory(Long cid);
    public Set<Category>  getCategories();
    public ApiResponse deleteCategory(Long cid);

}
