package com.example.demoproject.service;

import com.example.demoproject.entity.Order;
import com.example.demoproject.entity.User;
import com.example.demoproject.exception.BusinessException;
import com.example.demoproject.repository.OrderRepository;
import com.example.demoproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionDemoService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    public void createOrderWithoutTransaction(Long userId, Order order) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        order.setUser(user);
        order.setOrderTime(LocalDateTime.now());
        orderRepository.save(order);

        System.out.println("订单已创建, ID: "+order.getId());

        if(order.getQuantity()>100){
            throw new BusinessException("库存不足!");
        }
        System.out.println("库存扣减成功");
    }

    @Transactional
    public void createOrderWithTransaction(Long userId, Order order) {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            throw new BusinessException("用户不存在");
        }

        order.setUser(user);
        order.setOrderTime(LocalDateTime.now());
        orderRepository.save(order);
        System.out.println("订单已创建, ID: "+order.getId());

        if(order.getQuantity()>100){
            throw new BusinessException("库存不足!");
        }

        System.out.println("库存扣减成功");
    }
}
