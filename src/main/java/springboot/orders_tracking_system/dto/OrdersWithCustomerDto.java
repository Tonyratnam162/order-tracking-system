package springboot.orders_tracking_system.dto;

import java.time.LocalDate;

import springboot.orders_tracking_system.model.Customer;

public class OrdersWithCustomerDto {

    private LocalDate orderDate;
    private String status;
    private LocalDate deliveryDate;
    private Customer customer;

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

}
