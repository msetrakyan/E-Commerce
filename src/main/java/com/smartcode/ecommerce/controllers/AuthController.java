package com.smartcode.ecommerce.controllers;


import com.smartcode.ecommerce.model.user.UserEntity;
import com.smartcode.ecommerce.model.user.dto.UserCreateRequest;
import com.smartcode.ecommerce.model.user.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {





    @PostMapping(path = "/register")
    public ResponseEntity<UserDto> register(@RequestBody UserCreateRequest userCreateRequest) {

        return null;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<UserDto> login() {

        return null;
    }







}
