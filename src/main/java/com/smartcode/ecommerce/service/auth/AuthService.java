package com.smartcode.ecommerce.service.auth;

import com.smartcode.ecommerce.model.auth.AuthDto;
import com.smartcode.ecommerce.model.auth.AuthRequestDto;
import com.smartcode.ecommerce.model.user.dto.UserCreateRequest;
import com.smartcode.ecommerce.model.user.dto.UserDto;

public interface AuthService {

    UserDto register(UserCreateRequest userCreateRequest);

    AuthDto login(AuthRequestDto authRequestDto);


}
