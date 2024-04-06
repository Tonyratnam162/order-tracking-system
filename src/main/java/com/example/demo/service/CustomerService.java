package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public String deleteCustomerById(int id) {
        Customer customer=customerRepository.findById(id).orElse(null);
        if(customer!=null){
            customerRepository.deleteById(id);
             return "customer with given id is deleted";
        }
        return null;
    }

}
