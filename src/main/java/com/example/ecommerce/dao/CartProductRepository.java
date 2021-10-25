package com.example.ecommerce.dao;

import com.example.ecommerce.model.CartProduct;
import com.example.ecommerce.model.CartProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    CartProduct findByCartProductId(CartProductId cartProductId);
    void deleteByCartProductId(CartProductId cartProductId);
}
