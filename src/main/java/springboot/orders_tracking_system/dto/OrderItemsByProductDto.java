package springboot.orders_tracking_system.dto;

import java.time.LocalDate;

public class OrderItemsByProductDto {

    private String productName;
    private String customerName;
    private int quantity;
    private double price;
    private LocalDate orderDate;

    public OrderItemsByProductDto(String productName, String customerName, int quantity, double price,
            LocalDate orderDate) {
        this.productName = productName;
        this.customerName = customerName;
        this.quantity = quantity;
        this.price = price;
        this.orderDate = orderDate;
    }

    public OrderItemsByProductDto() {
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public LocalDate getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

}
