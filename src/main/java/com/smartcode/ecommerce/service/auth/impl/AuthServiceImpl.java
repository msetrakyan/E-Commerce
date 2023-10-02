package com.smartcode.ecommerce.service.auth.impl;

import com.smartcode.ecommerce.exception.DuplicationException;
import com.smartcode.ecommerce.mapper.UserMapper;
import com.smartcode.ecommerce.model.user.UserEntity;
import com.smartcode.ecommerce.model.user.dto.UserCreateRequest;
import com.smartcode.ecommerce.model.user.dto.UserDto;
import com.smartcode.ecommerce.repository.UserRepository;
import com.smartcode.ecommerce.service.auth.AuthService;
import com.smartcode.ecommerce.service.mail.MailService;
import com.smartcode.ecommerce.util.RandomGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final MailService mailService;
    private final UserMapper userMapper;



    public UserDto register(UserCreateRequest userCreateRequest) {

        if(userRepository.findByUsername(userCreateRequest.getUsername()) != null) {
            throw new DuplicationException(String.format("User by username: %s already exists", userCreateRequest.getUsername()));
        }

        UserEntity userEntity = userMapper.toEntity(userCreateRequest);

        userEntity.setCode(RandomGenerator.generateNumericString(6));
        userEntity.setIsVerified(false);
        userEntity.setBalance(new BigDecimal(0));

        userRepository.save(userEntity);

        mailService.sendMail(userEntity.getEmail(), "Verification", userEntity.getCode());

        return userMapper.toDto(userEntity);
    }






}
