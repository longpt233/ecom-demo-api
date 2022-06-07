package com.company.team.service.implement;

import com.company.team.data.entity.CategoryEntity;
import com.company.team.data.mapper.ProductMapper;
import com.company.team.data.entity.ProductEntity;
import com.company.team.data.request.ProductRequest;
import com.company.team.data.response.dto.ProductDto;
import com.company.team.exception.custom.InternalServerException;
import com.company.team.exception.custom.NotFoundException;
import com.company.team.repository.CategoryRepository;
import com.company.team.repository.ProductRepository;
import com.company.team.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Map<String, Object> getAllProducts(Integer page, Integer size) {

        Pageable pagingSort = PageRequest.of(page, size);

        Page<ProductEntity> pageTuts = productRepository.findAll(pagingSort);
        List<ProductEntity> productEntities = pageTuts.getContent();

        List<ProductDto> productDtos = productEntities.stream().map(ProductMapper::toProductModel).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("products", productDtos);
        response.put("currentPage", pageTuts.getNumber());
        response.put("totalItems", pageTuts.getTotalElements());
        response.put("totalPages", pageTuts.getTotalPages());

        return response;
    }

    @Override
    public ProductDto getProductBydId(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (!productEntity.isPresent()) {
            throw new NotFoundException("No product found");
        }
        return ProductMapper.toProductModel(productEntity.get());
    }

    @Override
    public ProductDto createProduct(ProductRequest productRequest) {


        ProductEntity product = ProductMapper.productReqToProductEntity(productRequest);

        CategoryEntity categoryEntity = categoryRepository.getOne(productRequest.getCategoryId());
        product.setCategory(categoryEntity);

        ProductEntity returnProductEntity = productRepository.saveAndFlush(product);
        return ProductMapper.toProductModel(returnProductEntity);

    }

    /**
     * tạm thời update bắt gửi lên hết thông tin
     * @param productRequest
     * @param id
     * @return
     */
    @Override
    public ProductDto updateProduct(ProductRequest productRequest, Long id) {

        Optional<ProductEntity> productExits = productRepository.findById(id);
        if (!productExits.isPresent()) throw new NotFoundException("product does not exist");
        // update
        ProductEntity productEntity = ProductMapper.productReqToProductEntityUpdate(productRequest, id);
        CategoryEntity categoryEntity = categoryRepository.getOne(productRequest.getCategoryId());
        productEntity.setCategory(categoryEntity);

        ProductEntity productUpdate;
        try {
            productUpdate = productRepository.saveAndFlush(productEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerException("Database error. Can't update product");
        }
        return ProductMapper.toProductModel(productUpdate);

    }

    @Override
    public void deleteProduct(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (!product.isPresent()) throw new NotFoundException("product does not exist");
        try {
            productRepository.deleteById(id);
        } catch (Exception ex) {
            throw new InternalServerException("Database error. Can't delete product");
        }
    }
}
