package com.scaler.projectservice.service;

import com.scaler.projectservice.faksstoreapi.FakeStoreClient;
import com.scaler.projectservice.faksstoreapi.FakeStoreProductResponse;
import com.scaler.projectservice.mapper.ProductMapper;
import com.scaler.projectservice.models.Product;
import com.scaler.projectservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("fakeStoreProductService")
public class FakeStoreProductService implements IProductService {

    RestTemplateBuilder restTemplate;
    private final FakeStoreClient fakeStoreClient;
    ProductRepository productRepository;

    public FakeStoreProductService(FakeStoreClient fakeStoreClient, ProductRepository productRepository) {
        this.fakeStoreClient = fakeStoreClient;
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long productId) {
        try {
            FakeStoreProductResponse fakeStoreProductResponse = fakeStoreClient.getProductById(productId);

            if (fakeStoreProductResponse == null){
                throw new Exception("fakeStoreProductResponse is null");
            }

            return ProductMapper.getProductFromFakeStoreProduct(fakeStoreProductResponse);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try {
            FakeStoreProductResponse[] fakeStoreProductResponseList =  fakeStoreClient.getProducts();
            if(fakeStoreProductResponseList == null){
                throw new Exception("response is null");
            }

            return ProductMapper.getProductsFromFakeStoreProductList(fakeStoreProductResponseList);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product saveProduct(Product product) {
        try {
            FakeStoreProductResponse response = fakeStoreClient.saveProduct(product);

            if (response == null){
                throw new Exception("response is null");
            }

            return ProductMapper.getProductFromFakeStoreProduct(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        try {
            FakeStoreProductResponse response = fakeStoreClient.updateProduct(productId, product);

            if (response == null){
                throw new Exception("response is null");
            }

            return ProductMapper.getProductFromFakeStoreProduct(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product patchProduct(Long productId, Product product) {
        try {
            FakeStoreProductResponse response = fakeStoreClient.patchProduct(productId, product);

            if (response == null){
                throw new Exception("response is null");
            }

            return ProductMapper.getProductFromFakeStoreProduct(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product deleteProductById(Long productId) {
        try {
            FakeStoreProductResponse response = fakeStoreClient.deleteProductById(productId);

            if (response == null){
                throw new Exception("response is null");
            }

            return ProductMapper.getProductFromFakeStoreProduct(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}