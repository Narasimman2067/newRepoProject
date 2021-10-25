package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product", schema = "shoes_shop")
public class Product {
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

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product",  fetch = FetchType.LAZY)
//    @JsonManagedReference(value =  "size-product")
//    private List<Size> sizes;
    @NotNull
    private int size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    @JsonBackReference(value = "product-category")
    private Category category;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adminId")
    @JsonBackReference(value = "admin-product")
    private Admin admin;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL) //bujena
//    @JsonManagedReference(value =  "product-cartProduct")
    @JsonIgnore
    private  Collection<CartProduct> cartProducts = new ArrayList<>();
}
