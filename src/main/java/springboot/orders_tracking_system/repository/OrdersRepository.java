package springboot.orders_tracking_system.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import springboot.orders_tracking_system.model.Customer;
import springboot.orders_tracking_system.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    List<Orders> findByCustomer(Customer customer);

    List<Orders> findByStatus(String status);

    @Query("select o from Orders o where o.orderDate > :date")
    List<Orders> getOrdersAfterAGivenDate(@Param(value = "date") LocalDate orderDate);

}
