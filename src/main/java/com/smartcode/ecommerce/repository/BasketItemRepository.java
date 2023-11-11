package com.smartcode.ecommerce.repository;

import com.smartcode.ecommerce.model.basket.BasketItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketItemRepository extends JpaRepository<BasketItemEntity, Integer> {
    BasketItemEntity findByUserIdAndProductId(Integer userId, Integer productId);
    List<BasketItemEntity> findAllByUserId(Integer userId);

    void deleteAllByUserId(Integer id);
}