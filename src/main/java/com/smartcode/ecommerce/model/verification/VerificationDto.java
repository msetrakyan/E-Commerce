package com.smartcode.ecommerce.model.verification;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerificationDto {


    @NotBlank
    private String username;

    @NotBlank
    private String code;


}
