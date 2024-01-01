package com.scaler.projectservice.dto;

import com.scaler.projectservice.models.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponseDTO {
    private String productName;
    private Category category;
    private String imageURL;
    private Double price;
    private String Description;
}
