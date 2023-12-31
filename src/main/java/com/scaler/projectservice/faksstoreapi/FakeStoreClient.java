package com.scaler.projectservice.faksstoreapi;

import com.scaler.projectservice.dto.ProductRequestDTO;
import com.scaler.projectservice.mapper.ProductMapper;
import com.scaler.projectservice.models.Product;
import com.scaler.projectservice.utility.HttpUtil;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreProductResponse saveProduct(ProductRequestDTO dto) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = new FakeStoreProductRequestDTO();
        fakeStoreProductRequestDTO.setTitle("Krishna");
        fakeStoreProductRequestDTO.setCategory("Noida");
        fakeStoreProductRequestDTO.setDescription("Desc");
        fakeStoreProductRequestDTO.setImage("");
        fakeStoreProductRequestDTO.setPrice(100.00);

        HttpEntity<FakeStoreProductRequestDTO> requestEntity = new HttpEntity<>(fakeStoreProductRequestDTO, headers);

        FakeStoreProductResponse response =  restTemplate.
                postForEntity("https://fakestoreapi.com/products",
                        requestEntity, FakeStoreProductResponse.class).getBody();

        return response;
    }
}
