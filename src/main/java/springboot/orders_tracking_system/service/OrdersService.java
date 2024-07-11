package springboot.orders_tracking_system.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import springboot.orders_tracking_system.dto.OrdersDto;
import springboot.orders_tracking_system.dto.OrdersWithCustomerDto;
import springboot.orders_tracking_system.model.Customer;
import springboot.orders_tracking_system.model.OrderItems;
import springboot.orders_tracking_system.model.Orders;
import springboot.orders_tracking_system.model.Product;
import springboot.orders_tracking_system.repository.CustomerRepository;
import springboot.orders_tracking_system.repository.OrderItemsRepository;
import springboot.orders_tracking_system.repository.OrdersRepository;
import springboot.orders_tracking_system.repository.ProductRepository;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderItemsRepository orderItemsRepository;

    public OrdersService(OrdersRepository ordersRepository, CustomerRepository customerRepository,
            ProductRepository productRepository, OrderItemsRepository orderItemsRepository) {
        this.ordersRepository = ordersRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderItemsRepository = orderItemsRepository;
    }

    public List<Orders> saveOrders(List<Orders> orders) {
        return ordersRepository.saveAll(orders);
    }

    public String createOrder(Orders orders) {
        Customer customer = customerRepository.findById(orders.getCustomer().getCustomerId()).orElse(null);
        if (customer != null) {
            Orders order = new Orders();
            order.setCustomer(customer);
            order.setOrderDate(orders.getOrderDate());
            order.setDeliveryDate(orders.getDeliveryDate());
            order.setStatus(orders.getStatus());
            ordersRepository.save(order);
            return "orders created successfully";
        }

        return null;
    }

    public Orders updateOrders(int id, Orders orders) {
        Orders ordersFromDB = ordersRepository.findById(id).orElse(null);
        if (ordersFromDB != null) {
            if (orders.getCustomer() != null) {
                ordersFromDB.setCustomer(orders.getCustomer());
            }
            if (orders.getDeliveryDate() != null) {
                ordersFromDB.setDeliveryDate(orders.getDeliveryDate());
            }
            if (orders.getOrderDate() != null) {
                ordersFromDB.setOrderDate(orders.getOrderDate());
            }
            if (orders.getOrderItems() != null) {
                ordersFromDB.setOrderItems(orders.getOrderItems());
            }
            if (orders.getStatus() != null) {
                ordersFromDB.setOrderItems(orders.getOrderItems());
            }
            return ordersRepository.save(ordersFromDB);

        }
        return null;

    }

    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public String deleteById(int orderId) {
        Orders orders = ordersRepository.findById(orderId).orElse(null);
        if (orders != null) {
            ordersRepository.deleteById(orderId);
            return "deleted Successfully";
        }
        return null;
    }

    public List<Orders> getOrdersByCustomerId(int customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            return ordersRepository.findByCustomer(customer);

        }
        return Collections.emptyList();
    }

    public List<Orders> getByStatus(String status) {
        return ordersRepository.findByStatus(status);
    }

    public List<Orders> getOrdersAfterGivenDate(LocalDate orderDate) {
        return ordersRepository.getOrdersAfterAGivenDate(orderDate);
    }

    public Orders getOrdersById(int orderId) {
        return ordersRepository.findById(orderId).orElse(null);
    }

    public String saveByOrdersDto(OrdersDto ordersDto) {
        Customer customer = customerRepository.findById(ordersDto.getCustomerId()).orElse(null);
        Orders orders = new Orders();
        orders.setCustomer(customer);
        orders.setDeliveryDate(LocalDate.parse("2024-04-06"));
        orders.setOrderDate(LocalDate.parse("2024-04-01"));
        orders.setStatus("n");
        ordersRepository.save(orders);
        for (Product product : ordersDto.getProducts()) {
            if (product != null) {
                productRepository.save(product);

                OrderItems orderItems = new OrderItems();
                orderItems.setProduct(product);
                orderItems.setQuantity(ordersDto.getQuantity());
                orderItems.setPrice(product.getProductPrice() * ordersDto.getQuantity());
                orderItems.setOrders(orders);
                orderItemsRepository.save(orderItems);

            }
        }

        return "orders created successfully";
    }

    public String createCustomersWithOrders(OrdersWithCustomerDto ordersWithCustomerDto) {

        Customer customer = new Customer();
        customer.setCustomerName(ordersWithCustomerDto.getCustomer().getCustomerName());
        customer.setCustomerMobile(ordersWithCustomerDto.getCustomer().getCustomerMobile());
        customer.setCustomerEmail(ordersWithCustomerDto.getCustomer().getCustomerEmail());
        customerRepository.save(customer);
        Orders orders = new Orders();
        orders.setOrderDate(ordersWithCustomerDto.getOrderDate());
        orders.setStatus(ordersWithCustomerDto.getStatus());
        orders.setCustomer(customer);
        orders.setDeliveryDate(ordersWithCustomerDto.getDeliveryDate());
        ordersRepository.save(orders);
        return "customer for order created";
    }

}
