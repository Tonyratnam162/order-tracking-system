package com.example.demo.repository;

import java.util.List;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Customer;
import com.example.demo.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findByCustomer(Customer customer);

    @Query("select o from Orders o where o.orderDate > :date")
    List<Orders> getOrdersAfterAGivenDate(@Param(value="date") LocalDate orderDate);

}
