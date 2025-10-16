package com.example.demoproject.controller;

import com.example.demoproject.common.Result;
import com.example.demoproject.entity.Order;
import com.example.demoproject.service.OrderService;
import com.example.demoproject.service.TransactionDemoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private TransactionDemoService transactionDemoService;

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

    @PostMapping("/test-no-transaction/user/{userId}")
    public Result<String> testNoTransaction(@PathVariable Long userId, @RequestBody Order order){
        transactionDemoService.createOrderWithoutTransaction(userId,order);
        return Result.success("操作成功(无事务)");
    }

    @PostMapping("/test-with-transaction/user/{userId}")
    public Result<String> testWithTransaction(@PathVariable Long userId, @RequestBody Order order){
        transactionDemoService.createOrderWithTransaction(userId,order);
        return Result.success("操作成功(有事务)");
    }
}
