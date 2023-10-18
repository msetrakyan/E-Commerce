package com.smartcode.ecommerce.model.auth;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthRequestDto {

    private String login;
    private String password;

}
