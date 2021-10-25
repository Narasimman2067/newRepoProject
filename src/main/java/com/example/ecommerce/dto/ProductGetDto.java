package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductGetDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @NotEmpty
    private String productName;

    @NotEmpty
    private String brand;

    @NotNull
    @Min(1000)
    private int price;

    @NotEmpty
    private String color;

    @NotEmpty
    private String type;

    @NotEmpty
    private String description;

    @NotEmpty
    private String image;

    @NotEmpty
    private int productQuantity;
    @NotNull
    private int size;

}
