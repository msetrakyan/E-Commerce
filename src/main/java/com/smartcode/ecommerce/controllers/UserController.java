package com.smartcode.ecommerce.controllers;

import com.smartcode.ecommerce.exception.UserAlreadyExistsException;
import com.smartcode.ecommerce.model.UserEntity;
import com.smartcode.ecommerce.service.mail.MailService;
import com.smartcode.ecommerce.service.user.UserService;
import com.smartcode.ecommerce.util.RandomGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MailService mailService;





    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity userEntity) {

//        if(userService.findById(userEntity.getId()) != null) {
//            throw new UserAlreadyExistsException("User already exists");
//        }

        String code = RandomGenerator.generateNumericString(6);
        userEntity.setCode(code);
        mailService.sendMail(userEntity.getEmail(), "Verification", code);
        userEntity.setBalance(new BigDecimal(0));
        userEntity.setIsVerified(false);

        return ResponseEntity.ok(userService.create(userEntity));
    }



    @GetMapping
    public ResponseEntity<List<UserEntity>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findById(id));
    }

//    @GetMapping(path = "/{username}")
//    public ResponseEntity<UserEntity> getUserByUsername(@PathVariable String username) {
//        return ResponseEntity.ok(userService.findByUsername(username));
//    }

    @DeleteMapping
    public ResponseEntity<UserEntity> delete(@RequestParam Integer id) {
        UserEntity byId = userService.findById(id);
        userService.delete(byId);
        return ResponseEntity.ok(byId);
    }

    @PatchMapping
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity userEntity) {
        userService.update(userEntity);
        return ResponseEntity.ok(userService.findById(userEntity.getId()));
    }

















}
