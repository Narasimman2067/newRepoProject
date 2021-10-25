package com.example.ecommerce.service;

import com.example.ecommerce.dao.ProductRepository;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.ProductGetDto;
import com.example.ecommerce.mapper.ProductMapper;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepo;
    @Autowired
    ProductMapper productMapper;



//    public Product saveProduct(Product product){
//        return productRepo.save(product);
//    }

    public ProductGetDto save(ProductDto productPostDto, MultipartFile file) throws IOException {

        UUID uuid =UUID.randomUUID();
        File saveFile = new ClassPathResource("static/productImage").getFile();
        Path path = Paths.get(saveFile.getAbsolutePath()+File.separator + uuid + file.getOriginalFilename());
        Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);

        Product newProduct = productMapper.dtoToModel(productPostDto);
        newProduct.setImage(uuid+file.getOriginalFilename());

        Product product = productRepo.save(newProduct);

        ProductGetDto productGetDto = productMapper.modelToGetDto(product);

        productGetDto.setImage(ServletUriComponentsBuilder.fromCurrentContextPath().path("/productImage/").path(productGetDto.getImage()).toUriString());

        return  productGetDto;
//        UserGetDto userGetDto = userMapper.modelToGetDto(user);
//        userGetDto.setImagePath(ServletUriComponentsBuilder.fromCurrentContextPath().path("/userimage/").path(userGetDto.getImagePath()).toUriString());
//        return null;
    }



    public List<Product> saveAllProduct(List<Product> products){
        return productRepo.saveAll(products);
    }

    public Product getProductById(long id){
        Product product = productRepo.findById(id).orElseThrow(EntityNotFoundException::new) ;
        product.setImage(ServletUriComponentsBuilder.fromCurrentContextPath().path("/productImage/").path(product.getImage()).toUriString());
        return  product;
    }

//    public List<ProductDto> getAllProduct(){
//        List<Product> product = productRepo.findAll();
//        List<ProductDto> products=product.stream().map(product1 -> {ProductDto ProductDtoList=new ProductDto();
//            ProductDtoList.setProductId(product1.getProductId());
//            ProductDtoList.setProductName(product1.getProductName());
//            ProductDtoList.setBrand(product1.getBrand());
//            ProductDtoList.setPrice(product1.getPrice());
//            ProductDtoList.setColor(product1.getColor());
//            ProductDtoList.setType(product1.getType());
//            ProductDtoList.setDescription(product1.getDescription());
//            ProductDtoList.setImage(product1.getImage());
//            ProductDtoList.setProductQuantity(product1.getProductQuantity());
//            ProductDtoList.setCategoryName(product1.getCategory().getCategoryName());
//            return ProductDtoList;
//        }).collect(Collectors.toList());
//       return products;
//    }

    public List<Product> getAllProducts(){
        List<Product> products = productRepo.findAll();
        for(Product p:products){
            p.setImage(ServletUriComponentsBuilder.fromCurrentContextPath().path("/productImage/").path(p.getImage()).toUriString());
        }
        return products;
    }
//    public Product updateProduct(Long id, Product updatedProduct){
//        Product existingProduct = productRepo.findById(id).orElseThrow(EntityNotFoundException::new);
//        existingProduct.setProductName(updatedProduct.getProductName());
//        existingProduct.setBrand(updatedProduct.getBrand());
//        existingProduct.setPrice(updatedProduct.getPrice());
//        existingProduct.setColor(updatedProduct.getColor());
//        existingProduct.setType(updatedProduct.getType());
//        existingProduct.setDescription(updatedProduct.getDescription());
//        existingProduct.setSize(updatedProduct.getSize());
//        existingProduct.setImage(updatedProduct.getImage());
//
//        return productRepo.save(existingProduct);
//    }
public String updateProduct(Long productId, ProductDto updatedProductPostDto, MultipartFile updatedFile) throws IOException {
    Product existingProduct = productRepo.findById(productId).orElseThrow(EntityNotFoundException::new);

    if (existingProduct != null) {
        UUID uuid = UUID.randomUUID();
        File saveFile = new ClassPathResource("static/productImage").getFile();
        Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + uuid + updatedFile.getOriginalFilename());
        Files.copy(updatedFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        Product newUpdatedProduct = productMapper.dtoToModel(updatedProductPostDto);
        newUpdatedProduct.setImage(uuid + updatedFile.getOriginalFilename());

        existingProduct.setProductName(newUpdatedProduct.getProductName());
        existingProduct.setBrand(newUpdatedProduct.getBrand());
        existingProduct.setPrice(newUpdatedProduct.getPrice());
        existingProduct.setColor(newUpdatedProduct.getColor());
        existingProduct.setType(newUpdatedProduct.getType());
        existingProduct.setDescription(newUpdatedProduct.getDescription());
        existingProduct.setImage(newUpdatedProduct.getImage());
        existingProduct.setProductQuantity(newUpdatedProduct.getProductQuantity());
        existingProduct.setSize(newUpdatedProduct.getSize());
        productRepo.save(existingProduct);

        return "Updated ID: " + productId;
    } else {
        return "Not Found";
    }
}


    public String updateById(long id, int productQuantity){
        productRepo.updateProductById(id, productQuantity);
        return "Product id:  "+id+" updated";
    }

    public String deleteProductById(long id){
        productRepo.deleteById(id);
        return "Product ID: "+id+" is deleted";
    }


}
