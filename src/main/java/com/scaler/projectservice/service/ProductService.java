package com.scaler.projectservice.service;

import com.scaler.projectservice.dto.ProductRequestDTO;
import com.scaler.projectservice.faksstoreapi.FakeStoreProductRequestDTO;
import com.scaler.projectservice.faksstoreapi.FakeStoreProductResponse;
import com.scaler.projectservice.mapper.ProductMapper;
import com.scaler.projectservice.models.Product;
import com.scaler.projectservice.utility.HttpUtil;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService implements IProductService {

    RestTemplateBuilder restTemplate;

    public ProductService(RestTemplateBuilder restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long productId) {
        FakeStoreProductResponse dto = restTemplate.build().
                getForEntity("https://fakestoreapi.com/products/{id}",
                        FakeStoreProductResponse.class, productId)
                .getBody();

        Product p = ProductMapper.getProductFromFakeStoreProduct(dto);
        return p;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductResponse[] dto =  restTemplate.build().
                getForEntity("https://fakestoreapi.com/products",
                        FakeStoreProductResponse[].class).getBody();

        if(dto == null){
            // TODO raise exception
        }

        List<Product> products = ProductMapper.getProductsFromFakeStoreProductList(dto);
        return products;
    }

    @Override
    public Product saveProduct(ProductRequestDTO dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = new FakeStoreProductRequestDTO();
        fakeStoreProductRequestDTO.setTitle("Krishna");
        fakeStoreProductRequestDTO.setCategory("Noida");
        fakeStoreProductRequestDTO.setDescription("Desc");
        fakeStoreProductRequestDTO.setImage("");
        fakeStoreProductRequestDTO.setPrice(100.00);

        HttpEntity<FakeStoreProductRequestDTO> requestEntity = new HttpEntity<>(fakeStoreProductRequestDTO, headers);

        FakeStoreProductResponse response =  restTemplate.build().
                postForEntity("https://fakestoreapi.com/products",
                        requestEntity, FakeStoreProductResponse.class).getBody();

        return ProductMapper.getProductFromFakeStoreProduct(response);
    }

    @Override
    public Product updateProduct(Long productId, ProductRequestDTO dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = new FakeStoreProductRequestDTO();
        fakeStoreProductRequestDTO.setTitle(dto.getProductName());
        fakeStoreProductRequestDTO.setCategory(dto.getCategory());
        fakeStoreProductRequestDTO.setDescription(dto.getDescription());
        fakeStoreProductRequestDTO.setImage(dto.getImageUrl());
        fakeStoreProductRequestDTO.setPrice(dto.getPrice());

        HttpEntity<FakeStoreProductRequestDTO> requestEntity = new HttpEntity<>(fakeStoreProductRequestDTO, headers);

        FakeStoreProductResponse updateProductResponse =  restTemplate.build().
                postForEntity("https://fakestoreapi.com/products",
                        requestEntity, FakeStoreProductResponse.class).getBody();

        return ProductMapper.getProductFromFakeStoreProduct(updateProductResponse);
    }

    @Override
    public Product patchProduct(Long productId, Product dto) throws Exception {
        Product existingProduct = getProductById(productId);

        if (Objects.isNull(existingProduct)){
            throw new Exception("Product does not exist");
        }

        FakeStoreProductRequestDTO requestDTO = new FakeStoreProductRequestDTO();
        requestDTO.setCategory(dto.getCategory());
        requestDTO.setPrice(dto.getPrice());
        requestDTO.setImage(dto.getImage());
        requestDTO.setTitle(dto.getTitle());

        ResponseEntity<FakeStoreProductResponse> response = HttpUtil.requestForEntity(
                restTemplate,
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                requestDTO, FakeStoreProductResponse.class, productId);

        return ProductMapper.getProductFromFakeStoreProduct(response.getBody());
    }
}