package springboot.orders_tracking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.orders_tracking_system.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByCustomerEmail(String customerEmail);

    boolean existsByCustomerMobile(long customerMobile);

}
