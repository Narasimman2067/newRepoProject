package com.example.ecommerce.service;

import com.example.ecommerce.dao.CustomerRepository;
import com.example.ecommerce.model.Customer;
import com.example.ecommerce.util.CustomerValidator;
import com.example.ecommerce.view.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepo;



    public Customer saveCustomer(Customer customer){

        return customerRepo.save(customer);
    }

    public Object registration(Customer customer) {
        /*
         * validate the email
         */
        if (!CustomerValidator.validateEmail(customer.getCustomerEmail())) {
            return new ResponseObject(5, "This is invaild email!");
        }

        /*
         *validate the first name
         */
        if (!CustomerValidator.validateFirstname(customer.getCustomerName())) {
            return new ResponseObject(6, "Customer Name Is Not Valid!");
        }
        /*
         * validate last name
         */
        if (!CustomerValidator.validateAddress(customer.getCustomerAddress())) {

            return new ResponseObject(7, "Address Is Not Valid!");
        }

        /*
         * validate password
         */

        if (!CustomerValidator.validatePassword(customer.getCustomerPassword())) {
            return new ResponseObject(8, "Invallid password!");
        }

        if (!CustomerValidator.validateMobile(customer.getCustomerPhone())) {
            return new ResponseObject(10, "Enter Valid Mobile Number!");
        }

        Customer user = customerRepo.findCustomerByCustomerEmail(customer.getCustomerEmail());

        if (user != null) {
            return new ResponseObject(9, "This user already  exists!");
        }

        user = new Customer();
        user.setCustomerName(customer.getCustomerName());
        user.setCustomerEmail(customer.getCustomerEmail());
        user.setCustomerAddress(customer.getCustomerAddress());
        user.setCustomerPhone(customer.getCustomerPhone());
        user.setCustomerPassword(customer.getCustomerPassword());
        customerRepo.save(user);

        return new ResponseObject(1, "you have sucessfully registered!");
    }

    public Object validate(String email, String password){
        List<Customer> customers = customerRepo.findCustomerByCustomerEmailAndCustomerPassword(email, password);
        if(customers.size() != 0){
            return customers;
        }
        else{
            return new ResponseObject(0, "Invalid email or password!");
        }

    }

    public Customer getCustomerById(long id){
        return customerRepo.findById(id).orElseThrow(EntityNotFoundException::new) ;
    }

    public List<Customer> getAllCustomer(){
        return customerRepo.findAll();
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer){
        Customer existingCustomer = customerRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        existingCustomer.setCustomerName(updatedCustomer.getCustomerName());
        existingCustomer.setCustomerAddress(updatedCustomer.getCustomerAddress());
        existingCustomer.setCustomerPhone(updatedCustomer.getCustomerPhone());
        existingCustomer.setCustomerEmail(updatedCustomer.getCustomerEmail());
        existingCustomer.setCustomerPassword(updatedCustomer.getCustomerPassword());
        return customerRepo.save(existingCustomer);
    }

    public String deleteCustomerById(long id){
       customerRepo.deleteById(id);
       return "Customer ID: "+id+" is deleted";
    }

}
