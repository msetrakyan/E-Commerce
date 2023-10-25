package com.smartcode.ecommerce.service.user.impl;

import com.smartcode.ecommerce.exception.DuplicationException;
import com.smartcode.ecommerce.exception.ResourceNotFoundException;
import com.smartcode.ecommerce.exception.VerificationException;
import com.smartcode.ecommerce.mapper.UserMapper;
import com.smartcode.ecommerce.model.action.ActionRequestDto;
import com.smartcode.ecommerce.model.action.ActionType;
import com.smartcode.ecommerce.model.user.UserFilterModel;
import com.smartcode.ecommerce.model.user.dto.UserCreateRequest;
import com.smartcode.ecommerce.model.user.dto.UserDto;
import com.smartcode.ecommerce.model.user.UserEntity;
import com.smartcode.ecommerce.model.verification.VerificationDto;
import com.smartcode.ecommerce.publish.ApplicationPublisher;
import com.smartcode.ecommerce.publish.event.ActionEvent;
import com.smartcode.ecommerce.repository.UserRepository;
import com.smartcode.ecommerce.service.user.UserService;
import com.smartcode.ecommerce.spec.UserSpecification;
import com.smartcode.ecommerce.util.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserSpecification userSpecification;
    private final ApplicationPublisher applicationPublisher;

    @Transactional
    public UserDto create(UserCreateRequest userCreateRequest) {

        if(userRepository.findByUsername(userCreateRequest.getUsername()) != null) {
            throw new DuplicationException(String.format("User by username: %s already exists", userCreateRequest.getUsername()));
        }
        UserEntity userEntity = userMapper.toEntity(userCreateRequest);
        userRepository.save(userEntity);

        ActionRequestDto actionRequestDto = new ActionRequestDto();
        actionRequestDto.setActionDate(LocalDateTime.now());
        actionRequestDto.setUserId(CurrentUser.getId());
        actionRequestDto.setActionType(ActionType.CREATE);
        actionRequestDto.setEntityType("User");
        applicationPublisher.publishActionEvent(actionRequestDto);

        return userMapper.toDto(userEntity);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        UserEntity userEntity = userRepository.findById(id).get();

        ActionRequestDto actionRequestDto = new ActionRequestDto();
        actionRequestDto.setActionDate(LocalDateTime.now());
        actionRequestDto.setUserId(CurrentUser.getId());
        actionRequestDto.setActionType(ActionType.DELETE);
        actionRequestDto.setEntityType("User");
        applicationPublisher.publishActionEvent(actionRequestDto);


        userRepository.delete(userEntity);
    }

    @Override
    @Transactional
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

        ActionRequestDto actionRequestDto = new ActionRequestDto();
        actionRequestDto.setActionDate(LocalDateTime.now());
        actionRequestDto.setUserId(CurrentUser.getId());
        actionRequestDto.setActionType(ActionType.UPDATE);
        actionRequestDto.setEntityType("User");
        applicationPublisher.publishActionEvent(actionRequestDto);

        return userMapper.toDto(findById);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findById(Integer id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() ->
                     new ResourceNotFoundException(String.format("User by id: %d not found", id)));
        return userMapper.toDto(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findByUsername(String username) {

        UserEntity userEntity = userRepository.findByUsername(username);

        if(userEntity == null) {
            throw new ResourceNotFoundException(String.format("User by username: %s does not exist", username));
        }

        return userMapper.toDto(userEntity);
    }

    @Transactional(readOnly = true)
    public UserDto findByEmail(String email) {

        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null) {
            throw new ResourceNotFoundException(String.format("User by Email: %s does not exist", email));
        }

        return userMapper.toDto(userEntity);
    }


    @Transactional(readOnly = true)
    public List<UserDto> findAll(UserFilterModel userFilterModel) {
        if(userFilterModel == null) {
            return userRepository.findAll().stream().map(userMapper::toDto).toList();
        }
        return userRepository.findAll(userSpecification.filterAndSearch(userFilterModel)).stream().map(userMapper::toDto).toList();
    }


    @Transactional
    public void verify(VerificationDto verificationDto) {
        UserEntity userEntity = userRepository.findByUsername(verificationDto.getUsername());
        if(userEntity == null) {
            throw new ResourceNotFoundException("User does not exist");
        }
        if(!userEntity.getIsVerified()) {
            throw new VerificationException("User already verified");
        }
        if(!userEntity.getCode().equals(verificationDto.getCode())) {
            throw new ResourceNotFoundException("Verification code is wrong");
        }
        userEntity.setIsVerified(true);
        userRepository.save(userEntity);
    }



}
