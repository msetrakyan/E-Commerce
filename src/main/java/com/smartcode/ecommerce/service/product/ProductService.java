package com.smartcode.ecommerce.service.product;

import com.smartcode.ecommerce.model.product.ProductEntity;
import com.smartcode.ecommerce.model.product.dto.ProductCreateRequest;
import com.smartcode.ecommerce.model.product.dto.ProductDto;

import java.util.List;

public interface ProductService {


    ProductDto create(ProductCreateRequest productCreateRequest);

    ProductDto update(ProductEntity productEntity);

    void delete(Integer id);

    List<ProductDto> findAll();

    ProductDto findById(Integer id);

    void buy(Integer productId);





}
