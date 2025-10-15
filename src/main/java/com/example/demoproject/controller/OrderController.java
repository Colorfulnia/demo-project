package com.example.demoproject.controller;

import com.example.demoproject.common.Result;
import com.example.demoproject.entity.Order;
import com.example.demoproject.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/user/{userId}")
    public Result<Order> createOrder(@PathVariable Long userId, @Valid @RequestBody Order order){
        return Result.success(orderService.createOrder(userId,order));
    }

    @GetMapping
    public Result<List<Order>> getAllOrders(){
        return Result.success(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public Result<Order> getOrderById(@PathVariable Long id){
        return Result.success(orderService.getOrderById(id));
    }

    @GetMapping("/user/{userId}")
    public Result<List<Order>> getOrdersByUserId(@PathVariable Long userId){
        return Result.success(orderService.getOrdersByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return Result.success("订单已删除");
    }
}
