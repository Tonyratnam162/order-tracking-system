package springboot.orders_tracking_system.model;

import java.time.LocalDate;
import java.util.ArrayList;
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

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @Column(nullable = false)
    private LocalDate orderDate;
    @Column(nullable = false, length = 1, columnDefinition = "CHAR(1) CHECK (status IN('N','n','D','d','C','c'))")
    private String status;
    @Column(nullable = false)
    private LocalDate deliveryDate;
    @ManyToOne
    @JoinColumn(name = "customerId")
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

    public Orders(int orderId, LocalDate orderDate, String status, LocalDate deliveryDate, Customer customer) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.status = status;
        this.deliveryDate = deliveryDate;
        this.customer = customer;
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

    public List<Integer> getOrderItemIds() {
        List<Integer> orderItemIds = new ArrayList<>();
        if (getOrderItems() != null) {
            for (OrderItems orderItem : getOrderItems()) {
                orderItemIds.add(orderItem.getId());
            }
        }
        return orderItemIds;  
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    public Orders(Customer customer, List<OrderItems> orderItems) {
        this.customer = customer;
        this.orderItems = orderItems;
    }

}
