package com.smartcode.ecommerce.repository;

import com.smartcode.ecommerce.model.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByUserId(Integer userId);
}