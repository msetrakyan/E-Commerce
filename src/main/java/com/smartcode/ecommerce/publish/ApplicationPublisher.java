package com.smartcode.ecommerce.publish;


import com.smartcode.ecommerce.model.action.ActionDto;
import com.smartcode.ecommerce.model.action.ActionRequestDto;
import com.smartcode.ecommerce.publish.event.ActionEvent;
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

    public void publishActionEvent(ActionRequestDto actionRequestDto) {
        ActionEvent actionEvent = new ActionEvent(this, actionRequestDto);
        applicationEventPublisher.publishEvent(actionEvent);
    }








}
