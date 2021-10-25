package com.example.ecommerce.Controller;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.CartProduct;
import com.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class CartController {
    @Autowired
    private CartService service;

    @GetMapping("/cart")
    private List<Cart> getAll(){
        return service.getAllCart();
    }
    @GetMapping("/cartById/{id}")
    private Cart getById(@PathVariable Long id){
        return service.getCartById(id);
    }

    @PostMapping("/saveCart")
    public ResponseEntity<Cart> setCart(@Valid @RequestBody Cart cart){
        System.out.println("-> "+cart);
        return new ResponseEntity<Cart>(
                service.save(cart), HttpStatus.CREATED);
    }

    @PutMapping("/updateCart/cartId={cartId}/productId={productId}/quantity={quantity}")
    public ResponseEntity<Collection<CartProduct>> updateCartQuantityById(@PathVariable Long cartId, @PathVariable Long productId, @PathVariable int quantity){
        return new ResponseEntity<Collection<CartProduct>>(
                service.updateCartQuantityById(cartId, productId, quantity), HttpStatus.CREATED);
    }

    @PatchMapping("/updateCartProductsById/{id}")
    public String updateCartProductsById(@PathVariable Long id, @RequestBody CartProduct cartProduct){
        System.out.println("IN side");
        return service.updateCartProductById(id, cartProduct);
    }

    @DeleteMapping("/deleteCartById/cartId={cartId}/productId={productId}")
    public String deleteCart(@PathVariable Long cartId, @PathVariable Long productId){
        return service.deleteCartProductById(cartId, productId);
    }
    @DeleteMapping("/deleteCart")
    public String deleteAll(){
        return service.deleteAll();
    }
}
