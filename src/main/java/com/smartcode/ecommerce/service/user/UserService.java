package com.smartcode.ecommerce.service.user;

import com.smartcode.ecommerce.model.user.UserFilterModel;
import com.smartcode.ecommerce.model.user.dto.UserCreateRequest;
import com.smartcode.ecommerce.model.user.dto.UserDto;
import com.smartcode.ecommerce.model.user.UserEntity;

import java.util.List;

public interface UserService {

    UserDto create(UserCreateRequest userEntity);

    void delete(Integer id);

    UserDto update(UserEntity userEntity);

    UserDto findById(Integer id);

    UserDto findByUsername(String username);

    UserDto findByEmail(String email);

    List<UserDto> findAll(UserFilterModel userFilterModel);




}
