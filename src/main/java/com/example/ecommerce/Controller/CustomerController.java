package com.example.ecommerce.Controller;

import com.example.ecommerce.model.Customer;
import com.example.ecommerce.service.CustomerService;
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
public class CustomerController {
    @Autowired
    private CustomerService service;

    @GetMapping("/customer")
    private List<Customer> getAll(){
        return service.getAllCustomer();
    }
    @GetMapping("/customerById/{id}")
    private Customer getById(@PathVariable Long id){
        return service.getCustomerById(id);
    }

    @PostMapping("/saveCustomer")
    public Object setCustomer(@Valid @RequestBody Customer customer){
        return service.registration(customer);
    }

    @GetMapping("/customer/login")
    public Object loginCustomer(@RequestParam("customerEmail") String customerEmail,@RequestParam("customerPassword") String customerPassword){
        return service.validate(customerEmail, customerPassword);
    }

    @PutMapping("/updateCustomerById/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        return new ResponseEntity<Customer>(
                service.updateCustomer(id, customer), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCustomerById/{id}")
    public String deleteCustomer(@PathVariable Long id){
        return service.deleteCustomerById(id);
    }
}
