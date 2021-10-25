package com.example.ecommerce.service;

import com.example.ecommerce.dao.CategoryRepository;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepo;

    public List<Category> saveAllCategory(List<Category> categories){
        return categoryRepo.saveAll(categories);
    }

    public Category saveCategory(Category category){
        return categoryRepo.save(category);
    }

//    public Category getCategoryById(long id){
//        return categoryRepo.findById(id).orElseThrow(EntityNotFoundException::new) ;
//    }
    public Category getCategoryById(long id){
        Category category = categoryRepo.findById(id).orElseThrow(EntityNotFoundException::new) ;
        for (Product p: category.getProducts()){
            p.setImage(ServletUriComponentsBuilder.fromCurrentContextPath().path("/productImage/").path(p.getImage()).toUriString());
        }
        return category;
    }

//    public List<Category> getAllCategory(){
//        return categoryRepo.findAll();
//    }
public List<Category> getAllCategory(){
    List<Category> category = categoryRepo.findAll();
    for(Category c:category){
        for (Product p: c.getProducts()){
            p.setImage(ServletUriComponentsBuilder.fromCurrentContextPath().path("/productImage/").path(p.getImage()).toUriString());
        }
    }

    return category;
}


    public Category updateCategory(Long id, Category updatedCategory){
        Category existingCategory = categoryRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        existingCategory.setCategoryName(updatedCategory.getCategoryName());
        return categoryRepo.save(existingCategory);
    }

    public String deleteCategoryById(long id){
        categoryRepo.deleteById(id);
        return "Category ID: "+id+" is deleted";
    }

}
