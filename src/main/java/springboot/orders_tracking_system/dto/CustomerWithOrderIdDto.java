package springboot.orders_tracking_system.dto;

import java.util.List;

import springboot.orders_tracking_system.model.Orders;

public class CustomerWithOrderIdDto {
    private int customerId;
    private String customerName;
    private String customerEmail;
    private long customerMobile;
    private List<Orders> orders;

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

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

}
