package springboot.orders_tracking_system.dto;

import java.util.List;

import springboot.orders_tracking_system.model.Product;

public class OrdersDto {
    private int customerId;
    private List<Product> products;
    private int quantity;

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
