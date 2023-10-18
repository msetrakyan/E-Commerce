package com.smartcode.ecommerce.publish.listener;


import com.smartcode.ecommerce.feign.NotificationFeign;
import com.smartcode.ecommerce.model.notification.EmailDto;
import com.smartcode.ecommerce.model.user.UserEntity;
import com.smartcode.ecommerce.publish.event.RegistrationEvent;
import com.smartcode.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationEventListener {

    private final NotificationFeign notificationFeign;
    private final UserRepository userRepository;

    @EventListener
    public void registrationEvent(RegistrationEvent registrationEvent) {
        UserEntity user = userRepository.findByEmail(registrationEvent.getEmail());
        notificationFeign.sendEmail(new EmailDto(user.getEmail(), "Verification", user.getCode()));
    }







}
