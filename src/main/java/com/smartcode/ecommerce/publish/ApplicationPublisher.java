package com.smartcode.ecommerce.publish;


import com.smartcode.ecommerce.publish.event.RegistrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishRegistrationEvent(String email) {
        RegistrationEvent registrationEvent = new RegistrationEvent(this, email);
        applicationEventPublisher.publishEvent(registrationEvent);
    }








}
