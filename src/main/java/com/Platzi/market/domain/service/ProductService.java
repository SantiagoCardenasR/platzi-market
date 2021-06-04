package com.Platzi.market.domain.service;

import com.Platzi.market.domain.Product;
import com.Platzi.market.domain.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAll() {
        return productRepo.getAll();
    }

    public Optional<List<Product>> getByCategory (int categoryId) {
        return productRepo.getByCategory(categoryId);
    }

    public Optional<List<Product>> getScarseProducts (int quantity) {
        return productRepo.getScarseProducts(quantity);
    }

    public Optional<Product> getProduct (int productId) {
        return productRepo.getProduct(productId);
    }

    public Product save (Product product) {
        return productRepo.save(product);
    }

    public boolean delete(int idProduct) {
        return getProduct(idProduct).map(product -> {
            productRepo.delete(idProduct);
            return true;
        }).orElse(false);
    }
}
