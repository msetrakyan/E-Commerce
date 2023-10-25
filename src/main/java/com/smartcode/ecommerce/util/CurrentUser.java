package com.smartcode.ecommerce.util;


import com.smartcode.ecommerce.model.user.details.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {
 
    public static Integer getId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication.getPrincipal().equals("anonymous")) {
            return null;
        }
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getCredentials();
        return myUserDetails.getId();
    }

}
