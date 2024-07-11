package springboot.orders_tracking_system.controller;

import java.util.List;

import org.springframework.data.domain.Page;
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

import springboot.orders_tracking_system.model.Product;
import springboot.orders_tracking_system.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            List<Product> products = null;
            return new ResponseEntity<>(products, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        try {
            productService.saveProduct(product);
            return new ResponseEntity<>("product created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("failed to create product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{productId}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<String> updateProducts(@PathVariable int productId, @RequestBody Product product) {
        try {
            Product updatedProduct = productService.updateProducts(productId, product);
            if (updatedProduct == null) {
                return new ResponseEntity<>("product with given productId is not found ", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("updated product successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("failed to update products", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{productId}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<String> deleteProductById(@PathVariable int productId) {
        try {
            String response = productService.deleteProductById(productId);
            if (response == null) {
                return new ResponseEntity<>("product with given id is not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("failed to delete the product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public Page<Product> getProductsByPagination(@PathVariable int pageNumber, @PathVariable int pageSize) {
        return productService.getProductsByPagination(pageNumber, pageSize);
    }
@GetMapping("/productName")
public ResponseEntity<List<Product>>getByProductName(@RequestParam String productName){
    try {
        List<Product> products= productService.getProductsByMatchedProductName(productName);
        return new ResponseEntity<>(products,HttpStatus.OK);
        
    } catch (Exception e) {
        List<Product> products=null;
        return new ResponseEntity<>(products,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

}
