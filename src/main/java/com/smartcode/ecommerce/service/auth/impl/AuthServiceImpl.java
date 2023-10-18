package com.smartcode.ecommerce.service.auth.impl;

import com.smartcode.ecommerce.configuration.security.jwt.JwtTokenProvider;
import com.smartcode.ecommerce.exception.DuplicationException;
import com.smartcode.ecommerce.mapper.UserMapper;
import com.smartcode.ecommerce.model.auth.AuthDto;
import com.smartcode.ecommerce.model.auth.AuthRequestDto;
import com.smartcode.ecommerce.model.user.UserEntity;
import com.smartcode.ecommerce.model.user.dto.UserCreateRequest;
import com.smartcode.ecommerce.model.user.dto.UserDto;
import com.smartcode.ecommerce.publish.ApplicationPublisher;
import com.smartcode.ecommerce.repository.UserRepository;
import com.smartcode.ecommerce.service.auth.AuthService;
import com.smartcode.ecommerce.util.RandomGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final ApplicationPublisher applicationPublisher;


    public UserDto register(UserCreateRequest userCreateRequest) {

        if(userRepository.findByUsername(userCreateRequest.getUsername()) != null) {
            throw new DuplicationException(String.format("User by username: %s already exists", userCreateRequest.getUsername()));
        }

        UserEntity userEntity = userMapper.toEntity(userCreateRequest);

        userEntity.setCode(RandomGenerator.generateNumericString(6));
        userEntity.setIsVerified(false);
        userEntity.setBalance(new BigDecimal(0));

        userRepository.save(userEntity);

        applicationPublisher.publishRegistrationEvent(userCreateRequest.getEmail());

        return userMapper.toDto(userEntity);
    }

    public AuthDto login(AuthRequestDto authRequestDto) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getLogin(), authRequestDto.getPassword()));

        UserEntity user = userRepository.findByUsername(authRequestDto.getLogin());

        String accessToken = jwtTokenProvider.generateAccessToken(user.getId(), user.getUsername(), user.getRole().getRole().getName());

        return new AuthDto(accessToken);
    }






}
