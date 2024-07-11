package springboot.orders_tracking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.orders_tracking_system.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
