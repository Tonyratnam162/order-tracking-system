package springboot.orders_tracking_system.controller;

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
import org.springframework.web.bind.annotation.RestController;

import springboot.orders_tracking_system.dto.OrderItemsByProductDto;
import springboot.orders_tracking_system.dto.OrderItemsDto;
import springboot.orders_tracking_system.model.OrderItems;
import springboot.orders_tracking_system.service.OrderItemsService;

@RestController
@RequestMapping("/orderItems")
public class OrderItemsController {

    private final OrderItemsService orderItemsService;

    public OrderItemsController(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> saveOrderItems(@RequestBody OrderItems orderItems) {
        try {
            String response = orderItemsService.saveOrderItem(orderItems);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("failed to create orderItem", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderItems>> getAllOrderItems() {
        try {
            List<OrderItems> orderItems = orderItemsService.getAllOrderItems();
            return new ResponseEntity<>(orderItems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/productIds")
    public ResponseEntity<List<OrderItemsByProductDto>> getAllOrderItemsByProductDto(int productId) {
        try {
            List<OrderItemsByProductDto> orderItemsByProductDto = orderItemsService
                    .getOrderItemsByGivenProduct(productId);
            return new ResponseEntity<>(orderItemsByProductDto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orderItemsDto")
    public ResponseEntity<List<OrderItemsDto>> getAllOrderItemsAsOrderItemsDto() {
        try {
            List<OrderItemsDto> orderItemsDto = orderItemsService.getAllOrderItemsInAOrder();
                   
            return new ResponseEntity<>(orderItemsDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateOrderItems(@PathVariable int id, @RequestBody OrderItems orderItems) {
        try {
            OrderItems updatedOrders = orderItemsService.updateOrderItems(id, orderItems);
            if (updatedOrders == null) {
                return new ResponseEntity<>("orderItems with given orderId is not found ", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("updated orderItems succesfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("failed to update orders", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteById(@PathVariable int id) {
      String response=orderItemsService.deleteById(id);
      if(response==null){
        return "not deleted";
      }
      return response;
    }

}
