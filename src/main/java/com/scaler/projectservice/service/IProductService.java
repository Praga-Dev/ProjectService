package com.scaler.projectservice.service;

import com.scaler.projectservice.dto.ProductRequestDTO;
import com.scaler.projectservice.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long productId);

    List<Product> getAllProducts();

    Product saveProduct(ProductRequestDTO dto);
    Product updateProduct(Long productId, ProductRequestDTO dto);
    Product patchProduct(Long productId, Product dto) throws Exception;
}
