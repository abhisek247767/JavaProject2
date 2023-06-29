package com.first.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class RestControllers {

    private List<Product> productList = new ArrayList<>();

    public RestControllers() {
        productList.add(new Product(100, "Mouse", 2000));
        productList.add(new Product(101, "Keyboard", 3000));
    }
    
    

    // Add new product
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        productList.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                productList.remove(product);
                return new ResponseEntity<>("Product deleted", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }
}
