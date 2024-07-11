package springboot.orders_tracking_system.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import springboot.orders_tracking_system.dto.CustomerWithOrderIdDto;
import springboot.orders_tracking_system.model.Customer;
import springboot.orders_tracking_system.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

 

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            List<Customer> customers = customerService.getCustomers();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        try {
            String response = customerService.createCustomer(customer);
            if (response == null) {
                return new ResponseEntity<>("incorrect fields entered", HttpStatus.CREATED);
            }
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>("failed to create customers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/orders/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createCustomerAndOrders(@RequestBody CustomerWithOrderIdDto customerWithOrderIdDto) {
        try {
            String response = customerService.saveOrderAndCustomer(customerWithOrderIdDto);

            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>("failed to create customer and orders from it",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/saveAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createCustomers(@RequestBody List<Customer> customers) {
        try {
            List<Customer> customersList = customerService.saveAllCustomers(customers);
            return new ResponseEntity<>("created customers:\n\n" + customersList, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("failed to create customers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{customerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateCustomers(@PathVariable int customerId, @RequestBody Customer customer) {
        try {
            Customer customer1 = customerService.updateCustomer(customerId, customer);
            if (customer1 == null) {
                return new ResponseEntity<>("customer with given customerId is not found ", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("updated customer successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("failed to update customers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/{customerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCustomer(@PathVariable int customerId) {
        try {
            String response = customerService.deleteCustomerById(customerId);
            if (response == null) {
                return new ResponseEntity<>("customer with given id is not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("failed to delete the customer", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
