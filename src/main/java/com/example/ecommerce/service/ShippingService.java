package com.example.ecommerce.service;

import com.example.ecommerce.dao.ShippingRepository;
import com.example.ecommerce.model.Shipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ShippingService {
    @Autowired
    ShippingRepository shippingRepo;

    public Shipping saveShipping(Shipping shipping){
        return shippingRepo.save(shipping);
    }

    public Shipping getShippingById(long id){
        return shippingRepo.findById(id).orElseThrow(EntityNotFoundException::new) ;
    }

    public List<Shipping> getAllShipping(){
        return shippingRepo.findAll();
    }

    public Shipping updateShipping(Long id, Shipping updatedShipping){
        Shipping existingShipping = shippingRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        existingShipping.setShippingAddress(updatedShipping.getShippingAddress());
        existingShipping.setDeliveryDate(updatedShipping.getDeliveryDate());

        return shippingRepo.save(existingShipping);
    }

    public String deleteShippingById(long id){
        shippingRepo.deleteById(id);

        return "Shipping ID: "+id+" is deleted";
    }


}
