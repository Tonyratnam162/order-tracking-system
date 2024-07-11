package springboot.orders_tracking_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import springboot.orders_tracking_system.model.Product;
import springboot.orders_tracking_system.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(@RequestBody Product product) {
        return productRepository.save(product);
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
            return productRepository.save(productFromDB);
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public String deleteProductById(int id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            productRepository.deleteById(id);
            return "product deleted successfully";
        }
        return null;
    }

    public Page<Product> getProductsByPagination(int pageNumber, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAll(pageable);
    }

    public List<Product>getProductsByMatchedProductName(String productName){
       List<Product>products= productRepository.findAll();
       List<Product>productsList=new ArrayList<>();
       for (Product product : products) {
        if(product.getProductName().contains(productName))
        {
            productsList.add(product);
        }
        
       }
       return productsList;
    }

}
