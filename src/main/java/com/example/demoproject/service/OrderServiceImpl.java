package com.example.demoproject.service;

import com.example.demoproject.entity.Order;
import com.example.demoproject.entity.User;
import com.example.demoproject.exception.BusinessException;
import com.example.demoproject.repository.OrderRepository;
import com.example.demoproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Order createOrder(Long userId,Order order){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            throw new BusinessException("用户不存在,ID: "+ userId);
        }

        order.setUser(user);
        order.setOrderTime(LocalDateTime.now());

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId){
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Order getOrderById(Long orderId){
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public void deleteOrder(Long orderId){
        orderRepository.deleteById(orderId);
    }

}
