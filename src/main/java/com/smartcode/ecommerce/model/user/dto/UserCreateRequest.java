package com.smartcode.ecommerce.model.user.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest extends BaseUserDto {

    @NotBlank
    private String password;







}
