package com.scaler.projectservice.service;

import com.scaler.projectservice.models.Product;
import com.scaler.projectservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("selfProductService")
public class SelfProductService implements IProductService {


    ProductRepository repo;

    public SelfProductService(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public Product getProductById(Long productId) {
        Product p = repo.findByProductId(productId);
        return p;
    }

    @Override
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product patchProduct(Long productId, Product product) throws Exception {
        return null;
    }

    @Override
    public Product deleteProductById(Long productId) {
        return null;
    }
}
