package springboot.orders_tracking_system.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    @Column(name = "customer_name", nullable = false)
    private String customerName;
    @Column(name = "customer_email", nullable = false, unique = true)
    private String customerEmail;
    @Column(name = "customer_mobile", length = 10, unique = true, nullable = false)
    private long customerMobile;
    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Orders> orders;

    public Customer() {
    }

    public Customer(int customerId, String customerName, String customerEmail, long customerMobile,
            List<Orders> orders) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerMobile = customerMobile;
        this.orders = orders;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return this.customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public long getCustomerMobile() {
        return this.customerMobile;
    }

    public void setCustomerMobile(long customerMobile) {
        this.customerMobile = customerMobile;
    }

    public List<Orders> getOrders() {
        return this.orders;

    }

    public List<Integer> getOrderIds() {
        List<Integer> orderIds = new ArrayList<>();
        if (getOrders() != null) {
            for (Orders orders1 : getOrders()) {
                orderIds.add(orders1.getOrderId());

            }
        }

        return orderIds;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "{" +
                " customerId='" + getCustomerId() + "'" +
                ", customerName='" + getCustomerName() + "'" +
                ", customerEmail='" + getCustomerEmail() + "'" +
                ", customerMobile='" + getCustomerMobile() + "'" +
                ", orders='" + getOrders() + "'" +
                "}";
    }

    public Customer(int customerId, String customerName, String customerEmail,
            long customerMobile) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerMobile = customerMobile;
    }

}
