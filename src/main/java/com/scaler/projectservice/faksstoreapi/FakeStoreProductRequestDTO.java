package com.scaler.projectservice.faksstoreapi;
import lombok.Data;

@Data
public class FakeStoreProductRequestDTO {
    Long id;
    String title;
    Double price;
    String category;
    String description;
    String image;
}
