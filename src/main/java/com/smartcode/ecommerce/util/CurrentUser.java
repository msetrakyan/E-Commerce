package com.smartcode.ecommerce.util;

import com.smartcode.ecommerce.model.user.details.MyUserDetails;
import com.smartcode.ecommerce.util.RoleEnum;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class CurrentUser {

    public static Integer getId() {
        var principal = (MyUserDetails)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return principal.getId();
    }

    public static String getRole() {
        var principal = (MyUserDetails)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return principal.getAuthorities()
                .stream()
                .map(Object::toString)
                .toList().get(0);
    }

    public static Boolean isUser(){
        return getRole().equals(RoleEnum.USER.getName());
    }

    public static Boolean isAdmin(){
        return getRole().equals(RoleEnum.ADMIN.getName());
    }
}
