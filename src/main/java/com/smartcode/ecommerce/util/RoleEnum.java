package com.smartcode.ecommerce.util;


import lombok.Getter;

@Getter
public enum RoleEnum {

    USER("USER"),
    ADMIN("ADMIN");

    RoleEnum(String name) {
        this.name = name;
    }

    private final String name;


}
