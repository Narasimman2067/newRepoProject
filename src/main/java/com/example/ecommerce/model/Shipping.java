package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipping", schema = "shoes_shop")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long shippingId;

    @NotEmpty
    private String shippingAddress;

    @NotNull
    private Date deliveryDate;

    @OneToOne(mappedBy = "shipping", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "order-shipping")
    private Orders order;
}
