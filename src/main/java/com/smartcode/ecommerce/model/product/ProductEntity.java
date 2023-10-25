package com.smartcode.ecommerce.model.product;

import com.smartcode.ecommerce.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
@Setter
@Getter
@Entity(name = "products")
public class ProductEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false)
    private LocalDate productionDate;

    @Column(nullable = false)
    private Boolean isInDeadline;



}