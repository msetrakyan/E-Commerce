package com.smartcode.ecommerce.service.basket;


import com.smartcode.ecommerce.model.basket.BasketItemResponseDto;

import java.util.List;

public interface BasketItemService{

    void addOrUpdate(Integer productId);

    List<BasketItemResponseDto> getBasket();
    void deleteFromBasket(Integer basketItemId);
}