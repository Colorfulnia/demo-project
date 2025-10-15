package com.example.demoproject.service;

import com.example.demoproject.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Long uesrId, Order order);
    List<Order> getAllOrders();
    List<Order> getOrdersByUserId(Long userId);
    Order getOrderById(Long id);
    void deleteOrder(Long id);
}
