package com.scaler.projectservice.controller;


import com.scaler.projectservice.dto.CreateProductRequestDTO;
import com.scaler.projectservice.faksstoreapi.FakeStoreProductResponse;
import com.scaler.projectservice.service.IProductService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/products")
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public HttpEntity<FakeStoreProductResponse> getProductById(@PathVariable("productId") Long productId) throws Exception {
        FakeStoreProductResponse data =  productService.getProductById(productId);

        try{
            if(Objects.isNull(data)){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
            headers.add("class-name", "integrating APIS");
            return new ResponseEntity<>(data,headers, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public HttpEntity<List<FakeStoreProductResponse>> getAllProducts(){
        List<FakeStoreProductResponse> responseList =  productService.getAllProducts();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @PostMapping("/")
    public HttpEntity<FakeStoreProductResponse> createProduct(@RequestBody CreateProductRequestDTO dto){
        FakeStoreProductResponse response = productService.saveProduct(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public HttpEntity<FakeStoreProductResponse> updateProduct(@PathVariable("productId") Long productId, @RequestBody CreateProductRequestDTO dto){
        FakeStoreProductResponse response = productService.updateProduct(productId, dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{productId}")
    public HttpEntity<FakeStoreProductResponse> patchProduct(@PathVariable("productId") Long productId, @RequestBody CreateProductRequestDTO dto){
        FakeStoreProductResponse response = productService.patchProduct(productId, dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}