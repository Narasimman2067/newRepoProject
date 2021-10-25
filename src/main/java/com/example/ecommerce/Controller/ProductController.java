package com.example.ecommerce.Controller;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.ProductGetDto;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/product")
    private List<Product> getAll(){

        return service.getAllProducts();
    }

    @GetMapping("/productById/{id}")
    private Product getById(@PathVariable Long id){
        System.out.println("->>>> "+id);
        return service.getProductById(id);
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<ProductGetDto> setProduct(@RequestParam("product") String product, @RequestParam("file") MultipartFile file) throws IOException {
        ProductDto productPostDto = new ObjectMapper().readValue(product, ProductDto.class);
        return new ResponseEntity<ProductGetDto>(
                service.save(productPostDto, file), HttpStatus.CREATED);
    }

    @PutMapping("/updateProductById/{id}")
    public String updateProducts(@PathVariable("id") Long productId,@RequestParam("product") String product, @RequestParam("file") MultipartFile file) throws IOException {
        ProductDto productDto = new ObjectMapper().readValue(product, ProductDto.class);

        return service.updateProduct(productId, productDto, file);
    }
    @PatchMapping("/updateProductQuantityById/{id}&{quantity}")
        public String updateProductQuantityById(@PathVariable long id, @PathVariable int quantity){
        return service.updateById(id, quantity);
}

    @DeleteMapping("/deleteProductById/{id}")
    public String deleteProduct(@PathVariable Long id){
        return service.deleteProductById(id);
    }

}
