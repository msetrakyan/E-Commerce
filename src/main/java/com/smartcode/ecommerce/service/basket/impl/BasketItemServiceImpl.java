package com.smartcode.ecommerce.service.basket.impl;

import com.smartcode.ecommerce.exception.ResourceNotFoundException;
import com.smartcode.ecommerce.mapper.BasketItemMapper;
import com.smartcode.ecommerce.model.action.ActionType;
import com.smartcode.ecommerce.model.basket.BasketItemEntity;
import com.smartcode.ecommerce.model.basket.BasketItemResponseDto;
import com.smartcode.ecommerce.model.product.ProductEntity;
import com.smartcode.ecommerce.repository.BasketItemRepository;
import com.smartcode.ecommerce.repository.ProductRepository;
import com.smartcode.ecommerce.repository.UserRepository;
import com.smartcode.ecommerce.service.action.ActionService;
import com.smartcode.ecommerce.service.basket.BasketItemService;
import com.smartcode.ecommerce.util.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketItemServiceImpl implements BasketItemService {

    private final BasketItemRepository basketItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ActionService actionService;
    private final BasketItemMapper basketItemMapper;

    @Override
    @Transactional
    public List<BasketItemResponseDto> getBasket() {
        List<BasketItemEntity> basketItems = basketItemRepository.findAllByUserId(CurrentUser.getId());
        return basketItemMapper.toResponseDtoList(basketItems);
    }

    @Override
    @Transactional
    public void deleteFromBasket(Integer basketItemId) {
        BasketItemEntity basketItem = basketItemRepository.findById(basketItemId).get();
        if (basketItem.getUser().getId().equals(CurrentUser.getId())){
            basketItemRepository.deleteById(basketItemId);
        }
    }

    @Override
    @Transactional
    public void addOrUpdate(Integer productId) {
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        BasketItemEntity basketItem = basketItemRepository.findByUserIdAndProductId(CurrentUser.getId(), productId);
        if (basketItem == null) {
            BasketItemEntity basketItemEntity = new BasketItemEntity();
            basketItemEntity.setCount(1);
            basketItemEntity.setUser(userRepository.findById(CurrentUser.getId()).get());
            basketItemEntity.setProduct(productEntity);
            actionService.createAction(ActionType.ADD_TO_BASKET, "Product", CurrentUser.getId());
            basketItemRepository.save(basketItemEntity);
        }
        else {
            basketItem.setCount(basketItem.getCount() + 1);
            actionService.createAction(ActionType.UPDATE_IN_BASKET, "Product", CurrentUser.getId());
            basketItemRepository.save(basketItem);
        }
    }
}
