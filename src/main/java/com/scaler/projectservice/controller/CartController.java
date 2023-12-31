package com.scaler.projectservice.controller;

import com.scaler.projectservice.dto.ProductRequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @PostMapping("/")
    public String CreateCart(@RequestBody ProductRequestDTO dto){
        return "Cart is created";
    }

    @GetMapping("/")
    public  String GetallCart(){
        return "itesm";
    }

    @GetMapping("/{id}")
    public Integer GetCartById(@PathVariable Integer id){
        return id;
    }

    @DeleteMapping("/{id}")
    public String DeleteById(@PathVariable Integer id){
        return "Deleted";



    }
}
