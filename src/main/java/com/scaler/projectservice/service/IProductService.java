package com.scaler.projectservice.service;

import com.scaler.projectservice.dto.CreateProductRequestDTO;
import com.scaler.projectservice.faksstoreapi.FakeStoreProductResponse;

import java.util.List;

public interface IProductService {
    FakeStoreProductResponse getProductById(Long productId);

    List<FakeStoreProductResponse> getAllProducts();

    FakeStoreProductResponse saveProduct(CreateProductRequestDTO dto);
    FakeStoreProductResponse updateProduct(Long productId, CreateProductRequestDTO dto);
    FakeStoreProductResponse patchProduct(Long productId, CreateProductRequestDTO dto);
}
