package springboot.orders_tracking_system.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import springboot.orders_tracking_system.dto.CustomerWithOrderIdDto;
import springboot.orders_tracking_system.model.Customer;
import springboot.orders_tracking_system.model.Orders;
import springboot.orders_tracking_system.repository.CustomerRepository;
import springboot.orders_tracking_system.repository.OrdersRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrdersRepository ordersRepository;

    public CustomerService(CustomerRepository customerRepository, OrdersRepository ordersRepository) {
        this.customerRepository = customerRepository;
        this.ordersRepository = ordersRepository;
    }

    public String createCustomer(Customer customer) {
        Pattern emailPattern = Pattern
                .compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher emailMatcher = emailPattern.matcher(customer.getCustomerEmail());
        Pattern mobilePattern = Pattern.compile("^\\d{10}$");
        Matcher mobileMatcher = mobilePattern.matcher(String.valueOf(customer.getCustomerMobile()));
        if (emailMatcher.matches() && mobileMatcher.matches()) {
            customerRepository.save(customer);
            return "customer created successfully";
        }
        if (emailMatcher.matches() && !mobileMatcher.matches()) {
            return "invalid mobile number";
        }
        if (!emailMatcher.matches() && mobileMatcher.matches()) {
            return "invalid email";
        }
        if (!emailMatcher.matches() && !mobileMatcher.matches()) {
            return "invalid email and mobile number";
        }

        return null;
    }

    public List<Customer> saveAllCustomers(List<Customer> customers) {
        return customerRepository.saveAll(customers);
    }

    public Customer updateCustomer(int customerId, Customer customer) {
        Customer customerFromDB = customerRepository.findById(customerId).orElse(null);
        if (customerFromDB != null) {
            if (customer.getCustomerName() != null) {
                customerFromDB.setCustomerName(customer.getCustomerName());
            }
            if (customer.getCustomerEmail() != null) {
                customerFromDB.setCustomerEmail(customer.getCustomerEmail());
            }
            if (customer.getCustomerMobile() != 0) {

                customerFromDB.setCustomerMobile(customer.getCustomerMobile());
            }
            return customerRepository.save(customerFromDB);

        }
        return null;
    }

    public String saveOrderAndCustomer(CustomerWithOrderIdDto customerWithOrderIdDto) {
        Customer customer = new Customer(customerWithOrderIdDto.getCustomerId(),
                customerWithOrderIdDto.getCustomerName(), customerWithOrderIdDto.getCustomerEmail(),
                customerWithOrderIdDto.getCustomerMobile());
        customerRepository.save(customer);
        for (Orders orders : customerWithOrderIdDto.getOrders()) {
            Orders orders1 = new Orders();
            orders1.setOrderDate(orders.getOrderDate());
            orders1.setStatus(orders.getStatus());
            orders1.setDeliveryDate(orders.getDeliveryDate());
            orders1.setCustomer(customer);
            ordersRepository.save(orders1);
        }

        return "created successfully";

    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public String deleteCustomerById(int id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customerRepository.deleteById(id);
            return "customer with given id is deleted";
        }
        return null;
    }
}
