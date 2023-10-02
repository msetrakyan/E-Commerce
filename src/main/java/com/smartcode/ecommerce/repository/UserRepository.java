package com.smartcode.ecommerce.repository;

import com.smartcode.ecommerce.model.user.UserEntity;
import com.smartcode.ecommerce.model.user.UserFilterModel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {


    UserEntity findByUsername(String username);
    UserEntity findByEmail (String email);

    List<UserEntity> findAll(Specification specification);


}