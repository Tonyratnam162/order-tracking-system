package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.OrderItems;
import com.example.demo.service.OrderItemsService;

@RestController
@RequestMapping("/orderItems")
public class OrderItemsController {

    private final OrderItemsService orderItemsService;

    public OrderItemsController(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    @PostMapping("/saveAll")
    public List<OrderItems> saveOrderItems(@RequestBody List<OrderItems> orderItems) {
        return orderItemsService.saveOrderItems(orderItems);
    }

    @GetMapping("/getAll")
    public List<OrderItems> getAllOrderItems() {
        return orderItemsService.getAllOrderItems();
    }

    @PutMapping("/update/{id}")
    public OrderItems updateOrderItems(@PathVariable int id, @RequestBody OrderItems orderItems) {
       
        return orderItemsService.updateOrderItems(id, orderItems);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        orderItemsService.deleteById(id);
    }

}
