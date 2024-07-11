package springboot.orders_tracking_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.orders_tracking_system.model.OrderItems;
import springboot.orders_tracking_system.model.Product;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {
    List<OrderItems> findByProduct(Product product);
}
