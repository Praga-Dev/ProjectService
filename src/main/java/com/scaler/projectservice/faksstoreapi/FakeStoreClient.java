package com.scaler.projectservice.faksstoreapi;

import com.scaler.projectservice.models.Product;
import com.scaler.projectservice.utility.HttpUtil;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;


@Service
public class FakeStoreClient {
    private final RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreProductResponse[] getProducts() {
        FakeStoreProductResponse[] response = restTemplateBuilder.build().
                getForEntity("https://fakestoreapi.com/products",
                        FakeStoreProductResponse[].class).getBody();

        return response;
    }

    public FakeStoreProductResponse getProductById(Long productId) {
        FakeStoreProductResponse response = restTemplateBuilder.build().
                getForEntity("https://fakestoreapi.com/products/{id}",
                        FakeStoreProductResponse.class, productId)
                .getBody();

        return response;
    }

    public FakeStoreProductResponse saveProduct(Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<FakeStoreProductRequestDTO> requestEntity = getFakeStoreProductRequestDTOHttpEntity(product, headers);

        FakeStoreProductResponse response = restTemplateBuilder.build().
                postForEntity("https://fakestoreapi.com/products",
                        requestEntity, FakeStoreProductResponse.class).getBody();

        return response;
    }

    public FakeStoreProductResponse updateProduct(Long productId, Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<FakeStoreProductRequestDTO> requestEntity = getFakeStoreProductRequestDTOHttpEntity(product, headers);

        FakeStoreProductResponse response = restTemplateBuilder.build().
                postForEntity("https://fakestoreapi.com/products",
                        requestEntity, FakeStoreProductResponse.class).getBody();

        return response;
    }

    public FakeStoreProductResponse patchProduct(Long productId, Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FakeStoreProductRequestDTO> requestEntity = getFakeStoreProductRequestDTOHttpEntity(product, headers);

        ResponseEntity<FakeStoreProductResponse> response = HttpUtil.requestForEntity(
                restTemplateBuilder,
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                requestEntity, FakeStoreProductResponse.class, productId);

        return response.getBody();
    }

    private static HttpEntity<FakeStoreProductRequestDTO> getFakeStoreProductRequestDTOHttpEntity(Product product, HttpHeaders headers) {
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = new FakeStoreProductRequestDTO();
        fakeStoreProductRequestDTO.setTitle(product.getTitle());
        fakeStoreProductRequestDTO.setCategory(product.getCategory());
        fakeStoreProductRequestDTO.setDescription(product.getDescription());
        fakeStoreProductRequestDTO.setImage(product.getImage());
        fakeStoreProductRequestDTO.setPrice(product.getPrice());

        HttpEntity<FakeStoreProductRequestDTO> requestEntity = new HttpEntity<>(fakeStoreProductRequestDTO, headers);
        return requestEntity;
    }

    public FakeStoreProductResponse deleteProductById(Long productId) {
        ResponseEntity<FakeStoreProductResponse> response = HttpUtil.requestForEntity(
                restTemplateBuilder,
                HttpMethod.DELETE,
                "https://fakestoreapi.com/products/{id}",
                null, FakeStoreProductResponse.class,
                productId);

        return response.getBody();
    }
}
