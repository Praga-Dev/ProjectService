package com.scaler.projectservice.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "products")
public class Product extends BaseModel{
    private String Title;
    private double Price;
    private String Description;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    private String Image;
    private String Rating;
}
