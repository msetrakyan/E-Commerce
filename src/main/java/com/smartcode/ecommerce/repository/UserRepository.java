package com.smartcode.ecommerce.repository;

import com.smartcode.ecommerce.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {


    UserEntity findByUsername(String username);
    UserEntity findByEmail (String email);

    List<UserEntity> findAll();



}