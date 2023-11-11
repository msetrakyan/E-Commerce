package com.smartcode.ecommerce.controllers;

import com.smartcode.ecommerce.model.auth.AuthDto;
import com.smartcode.ecommerce.model.auth.AuthRequestDto;
import com.smartcode.ecommerce.model.user.dto.UserCreateRequest;
import com.smartcode.ecommerce.model.user.dto.UserDto;
import com.smartcode.ecommerce.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping(path = "/register")
    @PermitAll
    public ResponseEntity<UserDto> register(@RequestBody UserCreateRequest userCreateRequest) {
        return ResponseEntity.ok(authService.register(userCreateRequest));
    }

    @PostMapping(path = "/login")
    @PermitAll
    public ResponseEntity<AuthDto> login(@RequestBody AuthRequestDto authRequestDto) {
        return ResponseEntity.ok(authService.login(authRequestDto));
    }

}
