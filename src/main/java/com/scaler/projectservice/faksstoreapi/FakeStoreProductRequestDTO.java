package com.scaler.projectservice.faksstoreapi;
import lombok.Data;

@Data
public class FakeStoreProductRequestDTO {
    String title;
    Double price;
    String description;
    String image;
    String category;
}
