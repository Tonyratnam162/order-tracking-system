package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAllCustomers() {
        try {
            List<Customer> customers = customerService.getCustomers();
            return new ResponseEntity<>("customers:\n\n" + customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("failed to fetch all customers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> createCustomers(@RequestBody List<Customer> customers) {
        try {
            List<Customer> customersList = customerService.saveAllCustomers(customers);
            return new ResponseEntity<>("created customers:\n\n" + customersList, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("failed to create customers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<String> updateCustomers(@PathVariable int customerId, @RequestBody Customer customer) {
        try {
            Customer customer1 = customerService.updateCustomer(customerId, customer);
            if (customer1 == null) {
                return new ResponseEntity<>("customer with given customerId is not found ", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("updated customer:\n\n" + customer1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("failed to update customers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/{customerId}")

    public ResponseEntity<String>deleteCustomer(@PathVariable int customerId){
        try {
            String response=customerService.deleteCustomerById(customerId);
            if(response==null)
            {
                return new ResponseEntity<>("customer with given id is not found",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("failed to delete the customer",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // @PostMapping("/saveAll")
    // public List<Customer> saveAllCustomers(@RequestBody List<Customer> customers)
    // {
    // return customerService.saveAllCustomers(customers);
    // }

    // @PutMapping("/{customerId}")
    // public Customer updateCustomer(@PathVariable int customerId, @RequestBody
    // Customer customer) {
    // return customerService.updateCustomer(customerId, customer);

    // }

    // @GetMapping("/getAll")
    // public List<Customer> getCustomers() {
    // return customerService.getCustomers();
    // }

    // @DeleteMapping("/delete/{id}")
    // public void deleteCustomerById(@PathVariable int id) {
    //     customerService.deleteCustomerById(id);
    // }

}
