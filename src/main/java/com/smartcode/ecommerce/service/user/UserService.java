package com.smartcode.ecommerce.service.user;

import com.smartcode.ecommerce.model.product.ProductEntity;
import com.smartcode.ecommerce.model.product.dto.ProductDto;
import com.smartcode.ecommerce.model.user.UserFilterModel;
import com.smartcode.ecommerce.model.user.dto.UserCreateRequest;
import com.smartcode.ecommerce.model.user.dto.UserDto;
import com.smartcode.ecommerce.model.user.UserEntity;
import com.smartcode.ecommerce.model.verification.VerificationDto;

import java.util.List;

public interface UserService {

    UserDto create(UserCreateRequest userEntity);

    void delete(Integer id);

    UserDto update(UserEntity userEntity);

    UserDto findById(Integer id);

    UserDto findByUsername(String username);

    UserDto findByEmail(String email);

    List<UserDto> findAll(UserFilterModel userFilterModel);

    void verify(VerificationDto verificationDto);

    List<ProductDto> getCart(Integer userId);




}
