package springboot.orders_tracking_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import springboot.orders_tracking_system.dto.OrderItemsByProductDto;
import springboot.orders_tracking_system.dto.OrderItemsDto;
import springboot.orders_tracking_system.model.OrderItems;
import springboot.orders_tracking_system.model.Orders;
import springboot.orders_tracking_system.model.Product;
import springboot.orders_tracking_system.repository.OrderItemsRepository;
import springboot.orders_tracking_system.repository.OrdersRepository;
import springboot.orders_tracking_system.repository.ProductRepository;

@Service
public class OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;
    private final OrdersRepository ordersRepository;

    private final ProductRepository productRepository;

    public OrderItemsService(OrderItemsRepository orderItemsRepository, ProductRepository productRepository,
            OrdersRepository ordersRepository) {
        this.orderItemsRepository = orderItemsRepository;
        this.productRepository = productRepository;
        this.ordersRepository = ordersRepository;
    }

    public String saveOrderItem(OrderItems orderItems) {
        Product product = productRepository.findById(orderItems.getProduct().getProductId()).orElse(null);
        Orders orders = ordersRepository.findById(orderItems.getOrders().getOrderId()).orElse(null);
        if (product != null && orders != null) {
            OrderItems orderItems2 = new OrderItems(orders, product, orderItems.getQuantity(),
                    product.getProductPrice() * orderItems.getQuantity());
            orderItemsRepository.save(orderItems2);
            return "created successfully";
        }
        return "failed to create orderItems";

    }

    public List<OrderItems> saveOrderItems(List<OrderItems> orderItems) {
        return orderItemsRepository.saveAll(orderItems);
    }

    public List<OrderItems> getAllOrderItems() {
        return orderItemsRepository.findAll();
    }

    public OrderItems updateOrderItems(int id, OrderItems orderItems) {
        OrderItems orderItemsfromDB = orderItemsRepository.findById(id).orElse(null);
        if (orderItemsfromDB != null) {
            if (orderItems.getOrders() != null) {
                orderItemsfromDB.setOrders(orderItems.getOrders());
            }
            if (orderItems.getPrice() != 0) {
                orderItemsfromDB.setPrice(orderItems.getPrice());
            }
            if (orderItems.getProduct() != null) {
                orderItemsfromDB.setProduct(orderItems.getProduct());
            }
            if (orderItems.getQuantity() != 0) {
                orderItemsfromDB.setQuantity(orderItems.getQuantity());
            }
            return orderItemsRepository.save(orderItemsfromDB);

        }
        return null;
    }

    public List<OrderItemsDto> getAllOrderItemsInAOrder() {
        List<OrderItems> orderItems = orderItemsRepository.findAll();
        List<OrderItemsDto> orderItemsDtoList = new ArrayList<>();
        for (OrderItems orderItems2 : orderItems) {
            OrderItemsDto orderItemsDto = new OrderItemsDto(orderItems2.getProduct().getProductName(),
                    orderItems2.getQuantity(), orderItems2.getProduct().getProductPrice() * orderItems2.getQuantity());
            orderItemsDtoList.add(orderItemsDto);
        }
        return orderItemsDtoList;
    }

    public List<OrderItemsByProductDto> getOrderItemsByGivenProduct(int productId) {
        Product product = productRepository.findById(productId).orElse(null);
        List<OrderItems> orderItems = orderItemsRepository.findByProduct(product);
        List<OrderItemsByProductDto> orderItemsDtoList = new ArrayList<>();
        for (OrderItems orderItems2 : orderItems) {
            if (product != null && orderItems2.getOrders() != null && orderItems2.getOrders().getCustomer() != null) {

                OrderItemsByProductDto orderItemsByProductDto = new OrderItemsByProductDto(product.getProductName(),
                        orderItems2.getOrders().getCustomer().getCustomerName(), orderItems2.getQuantity(),
                        orderItems2.getPrice(), orderItems2.getOrders().getOrderDate());
                orderItemsDtoList.add(orderItemsByProductDto);
            }
        }
        return orderItemsDtoList;
    }

    public String deleteById(int id) {
        OrderItems orderItems = orderItemsRepository.findById(id).orElse(null);
        if (orderItems != null) {
            orderItemsRepository.deleteById(id);
            return "deleted succesfully";
        }
        return null;
    }

}
