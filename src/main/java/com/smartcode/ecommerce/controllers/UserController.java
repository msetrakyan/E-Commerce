package com.smartcode.ecommerce.controllers;

import com.smartcode.ecommerce.model.UserEntity;
import com.smartcode.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;





    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity userEntity) {
        return ResponseEntity.status(200).body(userService.create(userEntity));
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
