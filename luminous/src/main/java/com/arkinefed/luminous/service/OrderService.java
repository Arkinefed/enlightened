package com.arkinefed.luminous.service;

import com.arkinefed.luminous.model.Order;
import com.arkinefed.luminous.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

//    public List<Order> findOrdersByUserId(long id) {
//        return orderRepository.findOrdersByUserId(id);
//    }
}
