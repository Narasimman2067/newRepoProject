package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer", schema = "shoes_shop")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;

    @NotEmpty
    private String customerName;

    @NotEmpty
    private String customerAddress;

    @NotEmpty
    private String customerPhone;

    @NotEmpty
    @Email
    private String customerEmail;

    @NotEmpty
    @Size(max = 8)
    private String customerPassword;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer",  fetch = FetchType.LAZY)
    @JsonManagedReference(value =  "customer-cart")
    private List<Cart> carts;

}
