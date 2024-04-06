package com.example.demo.controller;

import java.util.List;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Orders;
import com.example.demo.service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("/saveAll")
    public List<Orders> saveAllOrders(@RequestBody List<Orders> orders) {
        return ordersService.saveOrders(orders);
    }

    @PutMapping("/update/{id}")
    public Orders updateOrders(@PathVariable int id, @RequestBody Orders orders) {
        return ordersService.updateOrders(id, orders);

    }

    @GetMapping("/getAll")
    public List<Orders> getAllOrders() {
        return ordersService.getAllOrders();
    }

    @DeleteMapping("/delete/{orderId}")
    public void deleteById(@PathVariable int orderId) {
        ordersService.deleteById(orderId);
    }

    @GetMapping("getAll/{customerId}")
    public List<Orders> getOrdersByCustomerId(@PathVariable int customerId) {
        return ordersService.getOrdersByCustomerId(customerId);
    }

    @GetMapping("/AfterOrderDate")
    public List<Orders> getOrdersAfterGivenDate(@RequestParam LocalDate orderDate) {
        return ordersService.getOrdersAfterGivenDate(orderDate);

}
}
