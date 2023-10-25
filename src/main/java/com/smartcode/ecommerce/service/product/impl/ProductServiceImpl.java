package com.smartcode.ecommerce.service.product.impl;

import com.smartcode.ecommerce.exception.ResourceNotFoundException;
import com.smartcode.ecommerce.mapper.ProductMapper;
import com.smartcode.ecommerce.model.action.ActionRequestDto;
import com.smartcode.ecommerce.model.action.ActionType;
import com.smartcode.ecommerce.model.product.ProductEntity;
import com.smartcode.ecommerce.model.product.dto.ProductCreateRequest;
import com.smartcode.ecommerce.model.product.dto.ProductDto;
import com.smartcode.ecommerce.publish.ApplicationPublisher;
import com.smartcode.ecommerce.repository.ProductRepository;
import com.smartcode.ecommerce.service.product.ProductService;
import com.smartcode.ecommerce.util.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ApplicationPublisher applicationPublisher;


    @Override
    @Transactional
    public ProductDto create(ProductCreateRequest productCreateRequest) {
        ProductEntity productEntity = productMapper.toEntity(productCreateRequest);
        productRepository.save(productEntity);

        ActionRequestDto actionRequestDto = new ActionRequestDto();
        actionRequestDto.setActionDate(LocalDateTime.now());
        actionRequestDto.setUserId(CurrentUser.getId());
        actionRequestDto.setActionType(ActionType.CREATE);
        actionRequestDto.setEntityType("Product");
        applicationPublisher.publishActionEvent(actionRequestDto);


        return productMapper.toDto(productEntity);
    }

    @Override
    @Transactional
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

        ActionRequestDto actionRequestDto = new ActionRequestDto();
        actionRequestDto.setActionDate(LocalDateTime.now());
        actionRequestDto.setUserId(CurrentUser.getId());
        actionRequestDto.setActionType(ActionType.UPDATE);
        actionRequestDto.setEntityType("Product");
        applicationPublisher.publishActionEvent(actionRequestDto);

    return productMapper.toDto(findById);
    }

    @Transactional
    public void delete(Integer id) {
        ProductEntity productEntity = productRepository.findById(id).get();

        ActionRequestDto actionRequestDto = new ActionRequestDto();
        actionRequestDto.setActionDate(LocalDateTime.now());
        actionRequestDto.setUserId(CurrentUser.getId());
        actionRequestDto.setActionType(ActionType.DELETE);
        actionRequestDto.setEntityType("Product");
        applicationPublisher.publishActionEvent(actionRequestDto);

        productRepository.delete(productEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(productMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public ProductDto findById(Integer id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() ->
        new ResourceNotFoundException(String.format("User by id: %d does not exist", id))
        );
        return productMapper.toDto(productEntity);
    }


}
