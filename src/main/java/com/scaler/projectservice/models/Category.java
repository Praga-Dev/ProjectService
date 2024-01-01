package com.scaler.projectservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "categories")
public class Category extends BaseModel{
    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
