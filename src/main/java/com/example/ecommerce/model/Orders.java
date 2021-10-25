package com.example.ecommerce.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders", schema = "shoes_shop")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @NotNull
    private double totalAmount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartId")
    @JsonBackReference(value = "orders-cart")
    public Cart cart;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shippingId")
    @JsonBackReference(value = "order-shipping")
    public Shipping shipping;
}
