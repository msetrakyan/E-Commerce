package com.smartcode.ecommerce.model.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductDto extends BaseProductDto {

    private Boolean isInDeadline;



}
