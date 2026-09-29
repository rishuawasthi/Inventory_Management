package com.hcl.inventory.service;

import com.hcl.inventory.entity.Product;
import com.hcl.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Integer productId, Product product) {

        Product existingProduct =
                productRepository.findById(productId).orElse(null);

        if (existingProduct == null) {
            return null;
        }

        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductPrice(product.getProductPrice());
        existingProduct.setProductCategory(product.getProductCategory());

        return productRepository.save(existingProduct);
    }

    public boolean deleteProduct(Integer productId) {

        if (!productRepository.existsById(productId)) {
            return false;
        }

        productRepository.deleteById(productId);
        return true;
    }
}