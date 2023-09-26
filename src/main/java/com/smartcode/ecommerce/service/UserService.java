package com.smartcode.ecommerce.service;

import com.smartcode.ecommerce.model.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity create(UserEntity userEntity);

    void delete(UserEntity userEntity);

    void update(UserEntity userEntity);

    UserEntity findById(Integer id);

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    List<UserEntity> findAll();




}
