package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.ProductGetDto;
import com.example.ecommerce.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductDto modelToPostDto(Product product);

    ProductGetDto modelToGetDto(Product product);

    @InheritInverseConfiguration
    Product dtoToModel(ProductDto productPostDto);
}
