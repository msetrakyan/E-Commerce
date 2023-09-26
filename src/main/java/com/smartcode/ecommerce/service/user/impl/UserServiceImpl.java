package com.smartcode.ecommerce.service.user.impl;

import com.smartcode.ecommerce.exception.ResourceNotFoundException;
import com.smartcode.ecommerce.exception.UserAlreadyExistsException;
import com.smartcode.ecommerce.model.UserEntity;
import com.smartcode.ecommerce.repository.UserRepository;
import com.smartcode.ecommerce.service.mail.MailService;
import com.smartcode.ecommerce.service.user.UserService;
import com.smartcode.ecommerce.util.RandomGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MailService mailService;

    public UserEntity create(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public void delete(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }

    @Override
    public void update(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity findById(Integer id) {
        Optional<UserEntity> byId = userRepository.findById(id);
        if(byId != null) {
            return byId.get();
        } else {
            throw new ResourceNotFoundException("User id does not exist");
        }
    }

    @Override
    public UserEntity findByUsername(String username) {
        UserEntity byUsername = userRepository.findByUsername(username);
        if(byUsername != null) {
            return byUsername;
        } else {
            throw new ResourceNotFoundException("Username does not exist");
        }
    }

    public UserEntity findByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity != null) {
            return userEntity;
        } else {
            throw new ResourceNotFoundException("User with such email does not exist");
        }
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }


}