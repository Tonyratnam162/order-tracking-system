package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.model.Orders;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrdersRepository;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;

    public OrdersService(OrdersRepository ordersRepository, CustomerRepository customerRepository) {
        this.ordersRepository = ordersRepository;
        this.customerRepository = customerRepository;
    }

    public List<Orders> saveOrders(List<Orders> orders) {
        return ordersRepository.saveAll(orders);
    }

    public Orders updateOrders(int id, Orders orders) {
        Orders ordersFromDB = ordersRepository.findById(id).orElse(null);
        if (ordersFromDB != null) {
            if (orders.getCustomer() != null) {
                ordersFromDB.setCustomer(orders.getCustomer());
            }
            if (orders.getDeliveryDate() != null) {
                ordersFromDB.setDeliveryDate(orders.getDeliveryDate());
            }
            if (orders.getOrderDate() != null) {
                ordersFromDB.setOrderDate(orders.getOrderDate());
            }
            if (orders.getOrderItems() != null) {
                ordersFromDB.setOrderItems(orders.getOrderItems());
            }
            if (orders.getStatus() != null) {
                ordersFromDB.setOrderItems(orders.getOrderItems());
            }
        }
        return ordersRepository.save(ordersFromDB);

    }

    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public void deleteById(int orderId) {
        ordersRepository.deleteById(orderId);
    }

    public List<Orders> getOrdersByCustomerId(int customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        return ordersRepository.findByCustomer(customer);
    }

    public List<Orders> getOrdersAfterGivenDate(LocalDate orderDate) {
     return ordersRepository.getOrdersAfterAGivenDate(orderDate);
    }

}
