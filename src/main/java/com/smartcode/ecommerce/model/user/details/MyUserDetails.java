package com.smartcode.ecommerce.model.user.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private final Integer id;
    private final Collection<? extends GrantedAuthority> authorities;
    private final String password;
    private final String username;

    public MyUserDetails(Integer id, Collection<? extends GrantedAuthority> authorities, String password, String username) {
        this.id = id;
        this.authorities = authorities;
        this.password = password;
        this.username = username;
    }

    public static MyUserDetails build(Integer id, String username, String password, String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        return new MyUserDetails(
                id,
                authorities,
                password,
                username);
    }




    public Integer getId() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }




}