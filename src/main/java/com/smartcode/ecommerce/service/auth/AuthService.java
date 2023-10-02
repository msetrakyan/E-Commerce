package com.smartcode.ecommerce.service.auth;

import com.smartcode.ecommerce.model.user.dto.UserCreateRequest;
import com.smartcode.ecommerce.model.user.dto.UserDto;

public interface AuthService {

    UserDto register(UserCreateRequest userCreateRequest);



}
