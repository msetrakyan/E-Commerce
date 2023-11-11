package com.smartcode.ecommerce.service.order.impl;

import com.smartcode.ecommerce.exception.ResourceNotFoundException;
import com.smartcode.ecommerce.mapper.OrderMapper;
import com.smartcode.ecommerce.mapper.ProductMapper;
import com.smartcode.ecommerce.model.action.ActionType;
import com.smartcode.ecommerce.model.basket.BasketItemEntity;
import com.smartcode.ecommerce.model.order.OrderEntity;
import com.smartcode.ecommerce.model.order.OrderItemEntity;
import com.smartcode.ecommerce.model.order.OrderResponseDto;
import com.smartcode.ecommerce.model.product.ProductEntity;
import com.smartcode.ecommerce.repository.BasketItemRepository;
import com.smartcode.ecommerce.repository.OrderRepository;
import com.smartcode.ecommerce.repository.UserRepository;
import com.smartcode.ecommerce.service.action.ActionService;
import com.smartcode.ecommerce.service.order.OrderService;
import com.smartcode.ecommerce.service.payment.strategy.PaymentService;
import com.smartcode.ecommerce.util.CurrentUser;
import com.smartcode.ecommerce.util.OrderStatus;
import com.smartcode.ecommerce.util.PaymentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final BasketItemRepository basketItemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final OrderMapper orderMapper;
    private final ActionService actionService;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public OrderResponseDto createOrder(PaymentType paymentType, String note) {
        List<BasketItemEntity> list = basketItemRepository.findAllByUserId(CurrentUser.getId());

        OrderEntity order = createOrderAndSetUserStatusNote(note);
        Map<Integer, BigDecimal> map = puttingCountAndPriceInTheMap(list);
        BigDecimal totalPrice = countingTotalPrice(map);

        order.setTotalAmount(totalPrice);
        List<OrderItemEntity> orderItems = getOrderItemEntities(list, order);
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        System.out.println(paymentService.pay(paymentType, totalPrice));
        basketItemRepository.deleteAllByUserId(CurrentUser.getId());
        actionService.createAction(ActionType.CREATE, "Order", CurrentUser.getId());
        return orderMapper.toResponseDto(order);
    }

    @Override
    @Transactional
    public List<OrderResponseDto> getAllOrders() {
        List<OrderEntity> allOrders = orderRepository.findAllByUserId(CurrentUser.getId());
        return orderMapper.toResponseDtoList(allOrders);
    }

    @Override
    @Transactional
    public OrderResponseDto getOrderById(Integer orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        if (order.getUser().getId().equals(CurrentUser.getId())) {
            return orderMapper.toResponseDto(order);
        } else throw new ResourceNotFoundException("Order not found");
    }

    private OrderEntity createOrderAndSetUserStatusNote(String note) {
        OrderEntity order = new OrderEntity();
        order.setUser(userRepository.findById(CurrentUser.getId()).get());
        order.setStatus(OrderStatus.ACCEPTED);
        order.setNote(note);
        return order;
    }

    private static BigDecimal countingTotalPrice(Map<Integer, BigDecimal> map) {
        BigDecimal totalPrice = new BigDecimal(0);
        Set<Map.Entry<Integer, BigDecimal>> entries = map.entrySet();
        for (Map.Entry<Integer, BigDecimal> entry : entries) {
            BigDecimal multiply = entry.getValue().multiply(new BigDecimal(entry.getKey()));
            totalPrice = totalPrice.add(multiply);
        }
        return totalPrice;
    }

    private Map<Integer, BigDecimal> puttingCountAndPriceInTheMap(List<BasketItemEntity> list) {
        Map<Integer, BigDecimal> map = new HashMap<>();
        for (BasketItemEntity basketItemEntity : list) {
            map.put(basketItemEntity.getCount(), basketItemEntity.getProduct().getPrice());
        }
        return map;
    }

    private List<OrderItemEntity> getOrderItemEntities(List<BasketItemEntity> list, OrderEntity order) {
        List<OrderItemEntity> orderItems = new ArrayList<>();
        for (BasketItemEntity basketItemEntity : list) {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setOrder(order);
            orderItemEntity.setCount(basketItemEntity.getCount());
//            orderItemEntity.setProductDetails(basketItemEntity.getProduct().getId());
            ProductEntity product = basketItemEntity.getProduct();
            orderItemEntity.setProductDetails(productMapper.toProductDetails(product));
            orderItemEntity.setTotalPrice(basketItemEntity.getProduct().getPrice().multiply(new BigDecimal(basketItemEntity.getCount())));
            orderItems.add(orderItemEntity);
        }
        return orderItems;
    }
}
