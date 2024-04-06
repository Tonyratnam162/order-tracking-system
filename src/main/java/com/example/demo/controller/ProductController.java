package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("saveAll")
    public List<Product> saveAllProducts(@RequestBody List<Product> products) {
        return productService.saveAllProducts(products);
    }

    @PutMapping("/update/{id}")
    public Product updateProducts(@PathVariable int id, @RequestBody Product product) {

        return productService.updateProducts(id, product);

    }

    @GetMapping("/getAll")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable int id) {
        productService.deleteProductById(id);
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public Page<Product> getProductsByPagination(@PathVariable int pageNumber, @PathVariable int pageSize) {
        return productService.getProductsByPagination(pageNumber, pageSize);
    }

}
