package com.smartcode.ecommerce.service.auth.impl;

import com.smartcode.ecommerce.configuration.security.jwt.JwtTokenProvider;
import com.smartcode.ecommerce.exception.DuplicationException;
import com.smartcode.ecommerce.exception.ResourceNotFoundException;
import com.smartcode.ecommerce.exception.ValidationException;
import com.smartcode.ecommerce.mapper.UserMapper;
import com.smartcode.ecommerce.model.action.ActionRequestDto;
import com.smartcode.ecommerce.model.action.ActionType;
import com.smartcode.ecommerce.model.auth.AuthDto;
import com.smartcode.ecommerce.model.auth.AuthRequestDto;
import com.smartcode.ecommerce.model.user.UserEntity;
import com.smartcode.ecommerce.model.user.dto.UserCreateRequest;
import com.smartcode.ecommerce.model.user.dto.UserDto;
import com.smartcode.ecommerce.publish.ApplicationPublisher;
import com.smartcode.ecommerce.repository.RoleRepository;
import com.smartcode.ecommerce.repository.UserRepository;
import com.smartcode.ecommerce.service.auth.AuthService;
import com.smartcode.ecommerce.util.CurrentUser;
import com.smartcode.ecommerce.util.RandomGenerator;
import com.smartcode.ecommerce.util.RoleEnum;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final ApplicationPublisher applicationPublisher;
    private final RoleRepository roleRepository;


    @Transactional
    public UserDto register(UserCreateRequest userCreateRequest) {

        if(userRepository.findByUsername(userCreateRequest.getUsername()) != null) {
            throw new DuplicationException(String.format("User by username: %s already exists", userCreateRequest.getUsername()));
        }


        UserEntity userEntity = userMapper.toEntity(userCreateRequest);

        userEntity.setCode(RandomGenerator.generateNumericString(6));
        userEntity.setIsVerified(false);
        userEntity.setBalance(new BigDecimal(0));
        userEntity.setRole(roleRepository.findByRole(RoleEnum.USER));

        userRepository.save(userEntity);

        ActionRequestDto actionRequestDto = new ActionRequestDto();
        actionRequestDto.setActionDate(LocalDateTime.now());
        actionRequestDto.setUserId(userEntity.getId());
        actionRequestDto.setActionType(ActionType.CREATE);
        actionRequestDto.setEntityType("User");

        applicationPublisher.publishActionEvent(actionRequestDto);
        applicationPublisher.publishRegistrationEvent(userCreateRequest.getEmail());

        return userMapper.toDto(userEntity);
    }

    @Transactional(readOnly = true)
    public AuthDto login(AuthRequestDto authRequestDto) {

        String username = authRequestDto.getLogin();
        String password = authRequestDto.getPassword();

        UserEntity user = userRepository.findByUsername(username);

        if(user == null) {
            throw new ResourceNotFoundException("Username doesn't exist");
        }
        if(!user.getPassword().equals(password)) {
            throw new ValidationException("Wrong password");
        }


        String accessToken = jwtTokenProvider.generateAccessToken(user.getId(), user.getUsername(), user.getRole().getRole().getName());

        return new AuthDto(accessToken);
    }






}
