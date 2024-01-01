package com.scaler.projectservice.mapper;

import com.scaler.projectservice.dto.ProductRequestDTO;
import com.scaler.projectservice.dto.ProductResponseDTO;
import com.scaler.projectservice.faksstoreapi.FakeStoreProductResponse;
import com.scaler.projectservice.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductMapper {
    public static Product getProductFromFakeStoreProduct(FakeStoreProductResponse dto){
        if(Objects.isNull(dto)){
            return null;
        }

        Product p = new Product();
        p.setTitle(dto.getTitle());
//        p.setCategory(dto.getCategory());
        p.setPrice(dto.getPrice());
        p.setDescription(dto.getDescription());
        p.setImage(dto.getImage());

        return p;
    }

    public static List<Product> getProductsFromFakeStoreProductList(FakeStoreProductResponse[] dtoList){
        List<Product> products = new ArrayList<>();

        for(FakeStoreProductResponse dto: dtoList){
            products.add(getProductFromFakeStoreProduct(dto));
        }

        return products;
    }

    public static Product getProductFromCreateRequestDTO(ProductRequestDTO dto) {
        if(Objects.isNull(dto)){
            return null;
        }

        Product p = new Product();
        p.setTitle(dto.getProductName());
//        p.setCategory(dto.getCategory());
        p.setPrice(dto.getPrice());
        p.setDescription(dto.getDescription());
        p.setImage(dto.getImageUrl());

        return p;
    }

    public static ProductResponseDTO getProductResponseDTOFromProduct(Product product) {
        if(Objects.isNull(product)){
            return null;
        }

        ProductResponseDTO p = new ProductResponseDTO();
        p.setProductName(product.getTitle());
        p.setCategory(product.getCategory());
        p.setPrice(product.getPrice());
        p.setDescription(product.getDescription());
        p.setImageURL(product.getImage());

        return p;
    }

    public static List<ProductResponseDTO> getProductDTOListFromProducts(List<Product> products) {
        List<ProductResponseDTO> responseDTOS = new ArrayList<>();
        for (Product p : products) {
            responseDTOS.add(getProductResponseDTOFromProduct(p));
        }

        return responseDTOS;
    }
}
