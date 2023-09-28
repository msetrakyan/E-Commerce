package com.smartcode.ecommerce.model.user.dto;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseUserDto {

    @NotBlank
    private String name;
    @NotBlank
    private String lastname;

    private Integer age;
    @NotBlank
    private String username;

    @Email
    private String email;


}
