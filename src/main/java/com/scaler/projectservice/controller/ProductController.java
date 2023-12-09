package com.scaler.projectservice.controller;


import com.scaler.projectservice.dto.CreateProductRequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @PostMapping("/")
    public String createProduct(@RequestBody CreateProductRequestDTO dto){
        return dto.getProductName() + " Product Created";
    }

    @GetMapping("/")
    public String getAllProducts(){
        return "All Products";
    }

    @GetMapping("/{productId}")
    public String getProductById(@PathVariable("productId") Integer productId){
        return "Product is---"+productId;
    }

    @DeleteMapping("/{productId}")
    public String deleteProductById(@PathVariable("productId") Integer productId){
        return "product is Deleted";
    }

}
