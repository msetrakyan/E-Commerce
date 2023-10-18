package com.smartcode.ecommerce.model.notification;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
public class EmailDto {

        @NotBlank
        @Email
        private String email;
        @NotBlank
        private String subject;
        @NotBlank
        private String text;




}
