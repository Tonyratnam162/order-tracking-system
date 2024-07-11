package springboot.orders_tracking_system.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springboot.orders_tracking_system.dto.OrdersDto;
import springboot.orders_tracking_system.dto.OrdersWithCustomerDto;
import springboot.orders_tracking_system.model.Orders;
import springboot.orders_tracking_system.service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createOrder(@RequestBody Orders orders) {
        try {
            String response = ordersService.createOrder(orders);
            if (response == null) {
                return new ResponseEntity<>("incorrect fields entered", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("failed to create orders", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateOrders(@PathVariable int orderId, @RequestBody Orders orders) {
        try {
            Orders updatedOrders = ordersService.updateOrders(orderId, orders);
            if (updatedOrders == null) {
                return new ResponseEntity<>("order with given orderId is not found ", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("updated succesfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("failed to update orders", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Orders>> getAllOrders() {
        try {
            List<Orders> orders = ordersService.getAllOrders();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status")
    public ResponseEntity<List<Orders>> getAllOrdersByStatus(@RequestParam String status) {
        try {
            List<Orders> orders = ordersService.getByStatus(status);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @SuppressWarnings("null")
    @GetMapping("/orderIds")
    public ResponseEntity<Orders> getOrdersById(@RequestParam int orderId) {
        try {
            Orders orders = ordersService.getOrdersById(orderId);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            Orders orders = null;
            return new ResponseEntity<>(orders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/ordersDto")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> saveOrdersByOrdersDto(@RequestBody OrdersDto ordersDto) {
        try {
            String response = ordersService.saveByOrdersDto(ordersDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("failed to save all orders", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<String> deleteOrder(@PathVariable int orderId) {
        try {
            String response = ordersService.deleteById(orderId);
            if (response == null) {
                return new ResponseEntity<>("order with given id is not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("failed to delete the order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<List<Orders>> getAllOrdersById(@PathVariable int customerId) {
        try {
            List<Orders> response = ordersService.getOrdersByCustomerId(customerId);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/AfterOrderDate")
    public ResponseEntity<List<Orders>> getAllOrdersByOrderDate(@RequestParam LocalDate orderDate) {
        try {
            List<Orders> orders = ordersService.getOrdersAfterGivenDate(orderDate);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.emptyList(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/ordersWithCustomerDto")
    @PreAuthorize("hasRole('ADMIN')")

    public String postMethodName(@RequestBody OrdersWithCustomerDto ordersWithCustomerDto) {
        return ordersService.createCustomersWithOrders(ordersWithCustomerDto);

    }

}
