package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.OrderItems;
import com.example.demo.repository.OrderItemsRepository;

@Service
public class OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;

    public OrderItemsService(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    public List<OrderItems> saveOrderItems(List<OrderItems> orderItems) {
        return orderItemsRepository.saveAll(orderItems);
    }

    public List<OrderItems> getAllOrderItems() {
        return orderItemsRepository.findAll();
    }

    public OrderItems updateOrderItems(int id, OrderItems orderItems) {
        OrderItems orderItemsfromDB = orderItemsRepository.findById(id).orElse(null);
        if (orderItemsfromDB != null) {
            if (orderItems.getOrders() != null) {
                orderItemsfromDB.setOrders(orderItems.getOrders());
            }
            if (orderItems.getPrice() != 0) {
                orderItemsfromDB.setPrice(orderItems.getPrice());
            }
            if (orderItems.getProduct() != null) {
                orderItemsfromDB.setProduct(orderItems.getProduct());
            }
            if (orderItems.getQuantity() != 0) {
                orderItemsfromDB.setQuantity(orderItems.getQuantity());
            }
        }
        return orderItemsRepository.save(orderItemsfromDB);
    }

    public void deleteById(int id) {
        orderItemsRepository.deleteById(id);
    }

}
