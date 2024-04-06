package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> saveAllProducts(@RequestBody List<Product> products) {
        return productRepository.saveAll(products);
    }

    public Product updateProducts(int id, Product product) {

        Product productFromDB = productRepository.findById(id).orElse(null);
        if (productFromDB != null) {
            if (product.getProductName() != null) {
                productFromDB.setProductName(product.getProductName());
            }
            if (product.getProductDescription() != null) {
                productFromDB.setProductDescription(product.getProductDescription());
            }
            if (product.getOrderItems() != null) {
                productFromDB.setOrderItems(product.getOrderItems());
            }
            if (product.getProductPrice() != 0) {
                productFromDB.setOrderItems(product.getOrderItems());
            }
        }
        return productRepository.save(productFromDB);

    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    public Page<Product> getProductsByPagination(int pageNumber, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAll(pageable);
    }

}
