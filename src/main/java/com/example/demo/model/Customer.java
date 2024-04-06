package com.example.demo.model;

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
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1, initialValue = 100)
    private int customerId;
    @Column(name = "customer_name", nullable = false)
    private String customerName;
    @Column(name = "customer_email", nullable = false, unique = true)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String customerEmail;
    @Column(name = "customer_mobile", length = 10, unique = true, nullable = false)
    @Size(min = 10, max = 10)
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + customerId;
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((customerEmail == null) ? 0 : customerEmail.hashCode());
        result = prime * result + (int) (customerMobile ^ (customerMobile >>> 32));
        result = prime * result + ((orders == null) ? 0 : orders.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (customerId != other.customerId)
            return false;
        if (customerName == null) {
            if (other.customerName != null)
                return false;
        } else if (!customerName.equals(other.customerName))
            return false;
        if (customerEmail == null) {
            if (other.customerEmail != null)
                return false;
        } else if (!customerEmail.equals(other.customerEmail))
            return false;
        if (customerMobile != other.customerMobile)
            return false;
        if (orders == null) {
            if (other.orders != null)
                return false;
        } else if (!orders.equals(other.orders))
            return false;
        return true;
    }

}
