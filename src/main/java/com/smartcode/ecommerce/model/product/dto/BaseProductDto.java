package com.smartcode.ecommerce.model.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseProductDto {


    @NotBlank
    private String name;

    @NotBlank
    private String company;

    private Double price;

    @NotBlank
    private String category;

    private Integer count;





}
