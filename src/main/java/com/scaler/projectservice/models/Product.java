package com.scaler.projectservice.models;


import lombok.Data;

@Data
public class Product extends BaseModel{
    private String Title;
    private double Price;
    private String Description;
    private String Category;
    private String Image;
    private String Rating;
}
