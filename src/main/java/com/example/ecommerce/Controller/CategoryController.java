package com.example.ecommerce.Controller;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/category")
    private List<Category> getAll(){
        return service.getAllCategory();
    }

    @GetMapping("/categoryById/{id}")
    private Category getById(@PathVariable Long id){
        return service.getCategoryById(id);
    }

    @PostMapping("/saveCategories")
    public ResponseEntity<List<Category>> setCategories(@Valid @RequestBody List<Category> categories){
        return new ResponseEntity<List<Category>>(
                service.saveAllCategory(categories), HttpStatus.CREATED);
    }

    @PostMapping("/saveCategory")
    public ResponseEntity<Category> setCategory(@Valid @RequestBody Category category){
        return new ResponseEntity<Category>(
                service.saveCategory(category), HttpStatus.CREATED);
    }

    @PutMapping("/updateCategoryById/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category){
        return new ResponseEntity<Category>(
                service.updateCategory(id, category), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCategoryById/{id}")
    public String deleteCategory(@PathVariable Long id){
        return service.deleteCategoryById(id);
    }
}
