package com.smartcode.ecommerce.configuration;

import com.smartcode.ecommerce.model.roles.RoleEntity;
import com.smartcode.ecommerce.repository.RoleRepository;
import com.smartcode.ecommerce.repository.UserRepository;
import com.smartcode.ecommerce.util.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class InitConfiguration {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        createRoles();
    }

    private void createRoles() {
        if (!roleRepository.existsByRole(RoleEnum.ADMIN)) {
            RoleEntity admin = new RoleEntity();
            admin.setRole(RoleEnum.ADMIN);
            roleRepository.save(admin);
        }

        if (!roleRepository.existsByRole(RoleEnum.USER)) {
            RoleEntity user = new RoleEntity();
            user.setRole(RoleEnum.USER);
            roleRepository.save(user);
        }
    }

}