package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Orders {
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_sequence")
    @SequenceGenerator(name = "orders_sequence", sequenceName = "orders_sequence",allocationSize = 1,initialValue = 200)  
   private int orderId;
    @Column(nullable = false)
    private LocalDate orderDate;
    @Column(length = 1, columnDefinition = "CHAR(1) CHECK(status IN('N','n','D','d','C','c'))")
    private String status;
    @Column(nullable = false)
    private LocalDate deliveryDate;
    @ManyToOne
    @JoinColumn(name = "customerId",nullable = false)
    private Customer customer;
    @JsonIgnore
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderItems> orderItems;

    public Orders() {
    }

    public Orders(int orderId, LocalDate orderDate, String status, LocalDate deliveryDate, Customer customer,
            List<OrderItems> orderItems) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.status = status;
        this.deliveryDate = deliveryDate;
        this.customer = customer;
        this.orderItems = orderItems;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItems> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

}
