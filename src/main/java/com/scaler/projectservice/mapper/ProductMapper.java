package com.scaler.projectservice.mapper;

import com.scaler.projectservice.faksstoreapi.FakeStoreProductResponse;
import com.scaler.projectservice.models.Product;

import java.util.Objects;

public class ProductMapper {
    public Product getProductFromFakeStore(FakeStoreProductResponse dto){
        if(Objects.isNull(dto)){
            return null;
        }

        Product p = new Product();
        p.setTitle(dto.getTitle());
        p.setCategory(dto.getCategory());
        p.setPrice(dto.getPrice());
        p.setDescription(dto.getDescription());
        p.setImage(dto.getImage());

        return p;
    }
}
