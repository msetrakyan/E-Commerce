package com.smartcode.ecommerce.controllers;

import com.smartcode.ecommerce.model.user.UserEntity;
import com.smartcode.ecommerce.model.user.UserFilterModel;
import com.smartcode.ecommerce.model.user.dto.UserDto;
import com.smartcode.ecommerce.model.verification.VerificationDto;
import com.smartcode.ecommerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(UserFilterModel userFilterModel) {
        return ResponseEntity.ok(userService.findAll(userFilterModel));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @DeleteMapping
    public ResponseEntity<UserDto> delete(@RequestParam Integer id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify")
    public void verify(@RequestBody @Valid VerificationDto verificationDto) {
        userService.verify(verificationDto);
    }


















}
