package com.quizmania.controller;

import com.quizmania.entity.Category;
import com.quizmania.service.Impl.CategoryServiceImpl;
import org.apache.coyote.Response;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;


    //adding new category
    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        return new ResponseEntity<>(this.categoryServiceImpl.addCategory(category), HttpStatus.CREATED);
    }

    @GetMapping("/get/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable("categoryId") Long categoryId){
        return new ResponseEntity<>(this.categoryServiceImpl.getCategory(categoryId),HttpStatus.OK );
    }

    //updating category
    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestBody Category category){
        return new ResponseEntity<>(this.categoryServiceImpl.updateCategory(category), HttpStatus.OK );
    }

    //get all categories
    @GetMapping("/getAll")
    public ResponseEntity<Set<Category>> getCategories(){
        return new ResponseEntity<>(this.categoryServiceImpl.getCategories(), HttpStatus.OK);
    }

    //Delete Category
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Long categoryId){
        return new ResponseEntity<>(this.categoryServiceImpl.deleteCategory(categoryId),HttpStatus.OK);

    }
}
