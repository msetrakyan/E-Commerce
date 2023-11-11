package com.smartcode.ecommerce.model.order;

import com.smartcode.ecommerce.model.BaseEntity;
import com.smartcode.ecommerce.model.product.ProductDetails;
import com.smartcode.ecommerce.model.product.ProductDetailsConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_item")
public class OrderItemEntity extends BaseEntity {

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    /*@Column(nullable = false)
    private Integer productId;*/

    @Convert(converter = ProductDetailsConverter.class)
    @Column(columnDefinition = "jsonb")
    private ProductDetails productDetails;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrderEntity order;
}
