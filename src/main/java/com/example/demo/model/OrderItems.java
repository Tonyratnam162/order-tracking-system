package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderItems_sequence")
    @SequenceGenerator(name = "orderItems_sequence", sequenceName = "orderItems_sequence", allocationSize = 1, initialValue = 400)
    private int id;
    @ManyToOne
    @JoinColumn(name = "orderId", insertable = false, updatable = false)
    private Orders orders;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    @Column(nullable = false)
    private int quantity;
    private double price;

    public OrderItems() {
    }

    public OrderItems(int id, Orders orders, Product product, int quantity, double price) {
        this.id = id;
        this.orders = orders;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Orders getOrders() {
        return this.orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}