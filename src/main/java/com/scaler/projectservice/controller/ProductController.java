package com.scaler.projectservice.controller;

import com.scaler.projectservice.dto.ProductRequestDTO;
import com.scaler.projectservice.dto.ProductResponseDTO;
import com.scaler.projectservice.mapper.ProductMapper;
import com.scaler.projectservice.models.Product;
import com.scaler.projectservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    public ProductController(@Qualifier("selfProductService") IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public HttpEntity<ProductResponseDTO> getProductById(@PathVariable("productId") Long productId){
        Product data =  productService.getProductById(productId);

        try{
            if(Objects.isNull(data)){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            ProductResponseDTO responseDTO = ProductMapper.getProductResponseDTOFromProduct(data);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

//            MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
//            headers.add("class-name", "integrating APIS");
//            return new ResponseEntity<>(responseDTO,headers, HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public HttpEntity<List<ProductResponseDTO>> getAllProducts(){
        List<Product> responseList =  productService.getAllProducts();
        List<ProductResponseDTO> responseDTOList = ProductMapper.getProductDTOListFromProducts(responseList);
        return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
    }

    @PostMapping("/")
    public HttpEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO dto){
        Product response = productService.saveProduct(ProductMapper.getProductFromCreateRequestDTO(dto));
        ProductResponseDTO responseDTO = ProductMapper.getProductResponseDTOFromProduct(response);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public HttpEntity<ProductResponseDTO> updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductRequestDTO dto){
        Product response = productService.updateProduct(productId, ProductMapper.getProductFromCreateRequestDTO(dto));
        ProductResponseDTO responseDTO = ProductMapper.getProductResponseDTOFromProduct(response);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PatchMapping("/{productId}")
    public HttpEntity<ProductResponseDTO> patchProduct(@PathVariable("productId") Long productId, @RequestBody ProductRequestDTO dto) throws Exception {
        try {
            Product product = productService.patchProduct(productId,
                    ProductMapper.getProductFromCreateRequestDTO(dto));

            ProductResponseDTO responseDTO = ProductMapper.getProductResponseDTOFromProduct(product);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
//            e.printStackTrace();
            throw e;
        }
    }

    @DeleteMapping("/{productId}")
    public HttpEntity<ProductResponseDTO> deleteProductById(@PathVariable("productId") Long productId){

        try{
            Product product =  productService.deleteProductById(productId);


            ProductResponseDTO responseDTO = ProductMapper.getProductResponseDTOFromProduct(product);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}