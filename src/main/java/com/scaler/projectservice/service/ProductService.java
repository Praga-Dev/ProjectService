package com.scaler.projectservice.service;

import com.scaler.projectservice.dto.CreateProductRequestDTO;
import com.scaler.projectservice.faksstoreapi.FakeStoreProductRequestDTO;
import com.scaler.projectservice.faksstoreapi.FakeStoreProductResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService implements IProductService {

    RestTemplateBuilder restTemplate;

    public ProductService(RestTemplateBuilder restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public FakeStoreProductResponse getProductById(Long productId) {
        FakeStoreProductResponse dto = restTemplate.build().
                getForEntity("https://fakestoreapi.com/products/{id}",
                        FakeStoreProductResponse.class, productId)
                .getBody();

        return dto;
    }

    @Override
    public List<FakeStoreProductResponse> getAllProducts() {
        FakeStoreProductResponse[] dto =  restTemplate.build().
                getForEntity("https://fakestoreapi.com/products",
                        FakeStoreProductResponse[].class).getBody();

        return Arrays.asList(dto);
    }

    @Override
    public FakeStoreProductResponse saveProduct(CreateProductRequestDTO dto) {
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

        return response;
    }

    @Override
    public FakeStoreProductResponse updateProduct(Long productId, CreateProductRequestDTO dto) {
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

        return updateProductResponse;
    }

    @Override
    public FakeStoreProductResponse patchProduct(Long productId, CreateProductRequestDTO dto) {
        /*
         * ToDo: Fetch the Product and verify whether if Product exists.
         * If Not -- Throw exception
         */

        FakeStoreProductRequestDTO requestDTO = new FakeStoreProductRequestDTO();
        requestDTO.setCategory(dto.getCategory());
        requestDTO.setPrice(dto.getPrice());
        requestDTO.setImage(dto.getImageUrl());
        requestDTO.setTitle(dto.getProductName());

        ResponseEntity<FakeStoreProductResponse> response =  requestForEntity(HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                requestDTO,FakeStoreProductResponse.class,productId);

        return response.getBody();
    }

    // this method we have created by our OWN>
    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate t = restTemplate.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();

        RequestCallback requestCallback =t.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = t.responseEntityExtractor(responseType);
        return t.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
}