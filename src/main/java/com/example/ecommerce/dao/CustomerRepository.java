package com.example.ecommerce.dao;

import com.example.ecommerce.model.Admin;
import com.example.ecommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByCustomerEmail(String email);

//    @Query(value = "select * from Customer  where Customer_email=:CustomerEmail and customer_password=:customerPassword", nativeQuery = true)
//    List<Customer> getCustomerByCustomerEmailAndCustomerPassword(@Param("customerEmail") String customerEmail , @Param("customerPassword") String customerPassword);
List<Customer>  findCustomerByCustomerEmailAndCustomerPassword(String email, String password);
}
