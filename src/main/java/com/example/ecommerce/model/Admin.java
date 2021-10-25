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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admin", schema = "shoes_shop", uniqueConstraints= {@UniqueConstraint(columnNames={"adminEmail"})})
public class Admin {
    @Id //primray key of entity bhanera janaucha
    @GeneratedValue(strategy = GenerationType.IDENTITY)//automatic primary key generation ko lagi @Geneartedvalue. link cha
    private long adminId;

    @NotEmpty
    private String adminName;

    @NotEmpty
    @Email
    private String adminEmail;

    @NotEmpty
    @Size(max = 8)
    private String adminPassword;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admin", fetch = FetchType.LAZY)//link cha
    @JsonManagedReference(value = "admin-product")
    private List<Product> products;
}