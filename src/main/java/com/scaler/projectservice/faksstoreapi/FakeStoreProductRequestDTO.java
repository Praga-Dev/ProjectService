package com.scaler.projectservice.faksstoreapi;
import com.scaler.projectservice.models.Category;
import lombok.Data;

@Data
public class FakeStoreProductRequestDTO {
    Long id;
    String title;
    Double price;
    Category category;
    String description;
    String image;
}
