package com.example.ecommerce.service;

import com.example.ecommerce.dao.CartProductRepository;
import com.example.ecommerce.dao.CartRepository;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.CartProduct;
import com.example.ecommerce.model.CartProductId;
import com.example.ecommerce.model.Product;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@ToString
@Transactional
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartProductRepository cartProductRepository;

    @Autowired
    ProductService productService;

//    public Cart saveCart(Cart cart){
//        Cart newCart = new Cart();
//        newCart.setOrder(cart.getOrder());
//        newCart.setCustomer(cart.getCustomer());
//
//        newCart.getCartProducts().addAll((cart.getCartProducts()
//                .stream()
//                .map(cp -> {
//                    System.out.println(cp);
//                    Product pp = productService.getProductById(cp.getProduct().getProductId());
//                    CartProduct newCartProduct = new CartProduct();
//                    newCartProduct.setProduct(pp);
//                    newCartProduct.setCart(newCart);
//                    newCartProduct.setQuantity(cp.getQuantity());
//
//                    return newCartProduct;
//                }).collect(Collectors.toList())
//        ));
//        return cartRepository.save(newCart);
//
//    }

    public Cart save(Cart cart){

        Cart newCart = new Cart();
        newCart.setOrder(cart.getOrder());
        newCart.setCustomer(cart.getCustomer());

        newCart.getCartProducts().addAll((cart.getCartProducts()
                .stream()
                .map(cp -> {
                    System.out.println(cp);
                    Product pp = productService.getProductById(cp.getProduct().getProductId());
                    CartProduct newCartProduct = new CartProduct();
                    newCartProduct.setProduct(pp);
                    newCartProduct.setCart(newCart);
                    newCartProduct.setQuantity(cp.getQuantity());

                    return newCartProduct;
                }).collect(Collectors.toList())
        ));
        return cartRepository.save(newCart);

    }

    public List<Cart> getAllCart(){
        return cartRepository.findAll();
    }

    public Cart getCartById(long id){
        return cartRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public String  updateCartProductById(long id, CartProduct cartProduct){
        Cart existingCart = getCartById(id);
        Cart newCart = new Cart();
        Collection<CartProduct> newCartProducts = new ArrayList<>();
        System.out.println(cartProduct);
        newCart.setOrder(existingCart.getOrder());
        newCart.setCustomer(existingCart.getCustomer());
        newCartProducts.add(cartProduct);
        existingCart.getCartProducts().addAll((newCartProducts
                .stream()
                .map(cp -> {
                    System.out.println(cp);
                    Product pp = productService.getProductById(cp.getProduct().getProductId());
                    CartProduct newCartProduct = new CartProduct();
                    newCartProduct.setProduct(pp);
                    newCartProduct.setCart(existingCart);
                    newCartProduct.setQuantity(cp.getQuantity());

                    return newCartProduct;
                }).collect(Collectors.toList())
        ));
        cartRepository.save(existingCart);

        return "Cart ID: "+id+" Updated and added new product";
    }

    public Collection<CartProduct> updateCartQuantityById(Long cartId,Long productId, int quantity){
        Cart existingCart = cartRepository.findById(cartId).orElseThrow(EntityNotFoundException::new);
        CartProduct existingCartProduct = cartProductRepository.findByCartProductId(new CartProductId(cartId, productId));
        existingCartProduct.setQuantity(quantity);

        existingCart.getCartProducts().stream().map(e->{
            if(e.getCartProductId() == existingCartProduct.getCartProductId()){
                e.setQuantity(quantity);
            }

            return existingCart;
        }).collect(Collectors.toList());

        existingCart.setCartProducts(existingCart.getCartProducts());
        cartRepository.save(existingCart);

        return existingCart.getCartProducts();
    }


    public String deleteCartById(long id){
        cartRepository.deleteById(id);
        return "Cart ID: "+id+" is deleted";
    }

    public String deleteAll(){
        cartRepository.deleteAll();

        return "All Admin deleted";
    }
    public String deleteCartProductById(Long cartId,Long productId){
//        cartRepository.deleteById(id);.deleteCartProductById(new CartProductId(cartId, productId))
//        cartProductRepository.
        cartProductRepository.deleteByCartProductId(new CartProductId(cartId, productId));
        return "Cart deleted";
    }

}
