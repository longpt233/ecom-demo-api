package com.company.team.data.mapper;

import com.company.team.data.entity.ProductEntity;
import com.company.team.data.request.ProductRequest;
import com.company.team.data.response.dto.ProductDto;

import java.util.Set;

import static com.company.team.utils.json.JsonSerializable.*;

/**
 * map from entity -> model
 */

public class ProductMapper {

    public static ProductDto toProductModel(ProductEntity product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        product.setAvailable(product.getAvailable());
        productDto.setImageUrls(deserialize(product.getImageUrl(), Set.class));
        productDto.setColors(deserialize(product.getColor(), Set.class));
        productDto.setCategory(product.getCategory().getName());
        productDto.setRating(product.getRating());
        productDto.setSold(product.getSold());
        return productDto;
    }

    public static ProductEntity productReqToProductEntityUpdate(ProductRequest productRequest, long id) {
        ProductEntity product = productReqToProductEntity(productRequest);
        product.setId(id);
        return product;
    }

    public static ProductEntity productReqToProductEntity(ProductRequest productRequest) {
        ProductEntity product = new ProductEntity();
        product.setName(productRequest.getName());
        product.setAvailable(productRequest.getAvailable());
        product.setBrand(productRequest.getBrand());
        product.setColor(serialize(productRequest.getColor()));
        product.setPrice(productRequest.getPrice());
        product.setImageUrl(serialize(productRequest.getImageUrl()));
        product.setDescription(productRequest.getDescription());
        product.setRating(productRequest.getRating());
        product.setSold(productRequest.getSold());
        return product;
    }
}
