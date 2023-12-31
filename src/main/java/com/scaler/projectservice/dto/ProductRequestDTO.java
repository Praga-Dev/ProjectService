package com.scaler.projectservice.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequestDTO {
    private String productName;
    private String ImageUrl;
    private String Category;
    private String Description;
    private double Price;
}
