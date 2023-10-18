package com.smartcode.ecommerce.model.user.details;

import com.smartcode.ecommerce.model.user.UserEntity;
import com.smartcode.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(String
                    .format("User by username: %s does not exist", username));
        }
        return MyUserDetails
                .build(user.getId(), user.getUsername(),user.getPassword(), user.getRole().getRole().getName());

    }



}