package com.scaler.projectservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponseDTO {
    private String productName;
    private String category;
    private String imageURL;
    private Double price;
    private String Description;
}
