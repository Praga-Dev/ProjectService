package com.scaler.projectservice.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProductRequestDTO {
    private String productName;
    private String ImageUrl;
    private String Category;
    private double Price;

}
