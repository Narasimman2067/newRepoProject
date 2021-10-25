package com.example.ecommerce.Controller;

import com.example.ecommerce.model.Shipping;
import com.example.ecommerce.service.ShippingService;
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
public class ShippingController {
    @Autowired
    private ShippingService service;

    @GetMapping("/shipping")
    private List<Shipping> getAll(){
        return service.getAllShipping();
    }
    @GetMapping("/shippingById/{id}")
    private Shipping getById(@PathVariable Long id){
        return service.getShippingById(id);
    }

    @PostMapping("/saveShipping")
    public ResponseEntity<Shipping> setShipping(@Valid @RequestBody Shipping shipping){
        return new ResponseEntity<Shipping>(
                service.saveShipping(shipping), HttpStatus.CREATED);
    }

    @PutMapping("/updateShippingById/{id}")
    public ResponseEntity<Shipping> updateShipping(@PathVariable Long id, @RequestBody Shipping shipping){
        return new ResponseEntity<Shipping>(
                service.updateShipping(id, shipping), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteShippingById/{id}")
    public String deleteShipping(@PathVariable Long id){
        return service.deleteShippingById(id);
    }


}
