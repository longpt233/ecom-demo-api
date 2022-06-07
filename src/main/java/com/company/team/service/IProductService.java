package com.company.team.service;

import com.company.team.data.request.ProductRequest;
import com.company.team.data.response.dto.ProductDto;

import java.util.Map;

public interface IProductService {
    Map<String, Object> getAllProducts(Integer page, Integer size);

    ProductDto getProductBydId(Long id);

    ProductDto createProduct(ProductRequest productRequest);

    ProductDto updateProduct(ProductRequest productRequest, Long id);

    void deleteProduct(Long id);
}
