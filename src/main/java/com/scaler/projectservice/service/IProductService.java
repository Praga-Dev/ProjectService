package com.scaler.projectservice.service;

import com.scaler.projectservice.dto.ProductRequestDTO;
import com.scaler.projectservice.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long productId);

    List<Product> getAllProducts();

    Product saveProduct(Product product);
    Product updateProduct(Long productId, Product product);
    Product patchProduct(Long productId, Product product) throws Exception;
    Product deleteProductById(Long productId);
}
