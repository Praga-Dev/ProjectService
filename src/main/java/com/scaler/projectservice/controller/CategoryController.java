package com.scaler.projectservice.controller;

import com.scaler.projectservice.dto.CreateCategoryRequestDTO;
import com.scaler.projectservice.models.Category;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @GetMapping("/")
    public String getAllCategory(){
        return "All Categories";
    }

    @PostMapping("/")
    public String createCategory(@RequestBody CreateCategoryRequestDTO dto){
        return dto.getName() + " Category is created";
    }

    @GetMapping("/{categoryId}")
    public String getCategoryById(@PathVariable("categoryId") Integer categoryId){
        return "Category is --->";
    }

    @DeleteMapping("/{categoryId}")
    public String deleteCategoryId(@PathVariable("categoryId") Integer categoryId){
        return "Category is Deleted!!!";
    }
}
