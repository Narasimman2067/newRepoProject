package com.example.ecommerce.Controller;


import com.example.ecommerce.model.Orders;
import com.example.ecommerce.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class OrdersController {
    @Autowired
    private OrdersService service;

    @GetMapping("/order")
    private List<Orders> getAll(){
        return service.getAllOrder();
    }
    @GetMapping("/orderById/{id}")
    private Orders getById(@PathVariable Long id){
        return service.getOrderById(id);
    }

    @PostMapping("/saveOrder")
    public ResponseEntity<Orders> setOrder(@Valid @RequestBody Orders order){
        return new ResponseEntity<Orders>(
                service.saveOrder(order), HttpStatus.CREATED);
    }

    @PutMapping("/updateOrderById/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long id, @RequestBody Orders order){
        return new ResponseEntity<Orders>(
                service.updateOrder(id, order), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteOrderById/{id}")
    public String deleteOrder(@PathVariable Long id){
        return service.deleteOrderById(id);
    }

}
