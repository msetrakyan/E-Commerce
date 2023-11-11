package com.smartcode.ecommerce.repository;

import com.smartcode.ecommerce.model.order.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Integer> {
}