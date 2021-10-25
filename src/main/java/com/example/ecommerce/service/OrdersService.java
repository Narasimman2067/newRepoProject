package com.example.ecommerce.service;

import com.example.ecommerce.dao.OrdersRepository;
import com.example.ecommerce.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepository orderRepo;

    public Orders saveOrder(Orders order){
        return orderRepo.save(order);
    }

    public Orders getOrderById(long id){
        return orderRepo.findById(id).orElseThrow(EntityNotFoundException::new) ;
    }

    public List<Orders> getAllOrder(){
        return orderRepo.findAll();
    }

    public Orders updateOrder(Long id, Orders updatedOrder){
        Orders existingOrder = orderRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        existingOrder.setTotalAmount(updatedOrder.getTotalAmount());
        return orderRepo.save(existingOrder);
    }

    public String deleteOrderById(long id){
        orderRepo.deleteById(id);
        return "Order ID: "+id+" is deleted";
    }


}
