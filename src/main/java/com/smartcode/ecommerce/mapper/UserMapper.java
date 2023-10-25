package com.smartcode.ecommerce.mapper;


import com.smartcode.ecommerce.model.user.dto.UserCreateRequest;
import com.smartcode.ecommerce.model.user.dto.UserDto;
import com.smartcode.ecommerce.model.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserDto toDto(UserEntity userEntity);


    UserEntity toEntity(UserCreateRequest userCreateRequest);




}
