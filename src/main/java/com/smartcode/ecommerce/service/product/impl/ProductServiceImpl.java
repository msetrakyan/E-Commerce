package com.smartcode.ecommerce.service.product.impl;

import com.smartcode.ecommerce.exception.ResourceNotFoundException;
import com.smartcode.ecommerce.mapper.ProductMapper;
import com.smartcode.ecommerce.model.product.ProductEntity;
import com.smartcode.ecommerce.model.product.dto.ProductCreateRequest;
import com.smartcode.ecommerce.model.product.dto.ProductDto;
import com.smartcode.ecommerce.repository.ProductRepository;
import com.smartcode.ecommerce.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Override
    public ProductDto create(ProductCreateRequest productCreateRequest) {
        ProductEntity productEntity = productMapper.toEntity(productCreateRequest);
        productRepository.save(productEntity);
        return productMapper.toDto(productEntity);
    }

    @Override
    public ProductDto update(ProductEntity productEntity) {

    ProductEntity findById = productRepository.findById(productEntity.getId()).orElseThrow(() ->
    new ResourceNotFoundException(String.format("Product by id: %d does not exist", productEntity.getId()))
    );

    findById.setName(productEntity.getName());
    findById.setCategory(productEntity.getCategory());
    findById.setCompany(productEntity.getCompany());
    findById.setCount(productEntity.getCount());
    findById.setIsInDeadline(productEntity.getIsInDeadline());
    findById.setPrice(productEntity.getPrice());
    findById.setProductionDate(productEntity.getProductionDate());

    productRepository.save(findById);

    return productMapper.toDto(findById);
    }

    @Override
    public void delete(Integer id) {
        ProductEntity productEntity = productRepository.findById(id).get();
        productRepository.delete(productEntity);
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(productMapper::toDto).toList();
    }

    @Override
    public ProductDto findById(Integer id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() ->
        new ResourceNotFoundException(String.format("User by id: %d does not exist", id))
        );
        return productMapper.toDto(productEntity);
    }


}
