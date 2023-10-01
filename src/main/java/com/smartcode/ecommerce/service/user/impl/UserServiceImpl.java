package com.smartcode.ecommerce.service.user.impl;

import com.smartcode.ecommerce.exception.DuplicationException;
import com.smartcode.ecommerce.exception.ResourceNotFoundException;
import com.smartcode.ecommerce.mapper.UserMapper;
import com.smartcode.ecommerce.model.user.dto.UserCreateRequest;
import com.smartcode.ecommerce.model.user.dto.UserDto;
import com.smartcode.ecommerce.model.user.UserEntity;
import com.smartcode.ecommerce.repository.UserRepository;
import com.smartcode.ecommerce.service.mail.MailService;
import com.smartcode.ecommerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MailService mailService;
    private final UserMapper userMapper;

    public UserDto create(UserCreateRequest userCreateRequest) {

        if(userRepository.findByUsername(userCreateRequest.getUsername()) != null) {
            throw new DuplicationException(String.format("User by username: %s already exists", userCreateRequest.getUsername()));
        }
        UserEntity userEntity = userMapper.toEntity(userCreateRequest);
        userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }

    @Override
    public void delete(Integer id) {
        UserEntity userEntity = userRepository.findById(id).get();
        userRepository.delete(userEntity);
    }

    @Override
    public UserDto update(UserEntity userEntity) {

        UserEntity findById = userRepository.findById(userEntity.getId()).orElseThrow(() ->
        new ResourceNotFoundException(String.format("User by id: %d does not exist", userEntity.getId())));

        findById.setLastname(userEntity.getLastname());
        findById.setName(userEntity.getName());
        findById.setAge(userEntity.getAge());
        findById.setEmail(userEntity.getEmail());
        findById.setPassword(userEntity.getPassword());
        findById.setUsername(userEntity.getUsername());
        findById.setBalance(userEntity.getBalance());

        userRepository.save(findById);

        return userMapper.toDto(findById);
    }

    @Override
    public UserDto findById(Integer id) {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("User by id: %d does not exist", id))
                );

        return userMapper.toDto(userEntity);
    }

    @Override
    public UserDto findByUsername(String username) {

        UserEntity userEntity = userRepository.findByUsername(username);

        if(userEntity == null) {
            throw new ResourceNotFoundException(String.format("User by username: %s does not exist", username));
        }

        return userMapper.toDto(userEntity);
    }

    public UserDto findByEmail(String email) {

        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null) {
            throw new ResourceNotFoundException(String.format("User by Email: %s does not exist", email));
        }

        return userMapper.toDto(userEntity);
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }



}
