package com.hcl.inventory.controller;

import com.hcl.inventory.entity.Product;
import com.hcl.inventory.exception.ProductNotFoundException;
import com.hcl.inventory.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET /products/{productId}
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(
            @PathVariable Integer productId) {

        Product product = productService.getProductById(productId);

        if (product == null) {
            throw new ProductNotFoundException(
                    "Product with id " + productId + " not found"
            );
        }

        return ResponseEntity.ok(product);
    }

    // POST /products
    @PostMapping
    public ResponseEntity<Product> addProduct(
            @Valid @RequestBody Product product) {

        Product savedProduct = productService.addProduct(product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedProduct);
    }

    // PUT /products/{productId}
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Integer productId,
            @Valid @RequestBody Product product) {

        Product updatedProduct =
                productService.updateProduct(productId, product);

        if (updatedProduct == null) {
            throw new ProductNotFoundException(
                    "Product with id " + productId + " not found"
            );
        }

        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE /products/{productId}
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Integer productId) {

        boolean deleted = productService.deleteProduct(productId);

        if (!deleted) {
            throw new ProductNotFoundException(
                    "Product with id " + productId + " not found"
            );
        }

        return ResponseEntity.noContent().build();
    }
}